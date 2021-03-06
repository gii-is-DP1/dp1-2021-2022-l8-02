package org.springframework.samples.endofline.game;


import java.time.Duration;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.board.exceptions.NotUrTurnException;
import org.springframework.samples.endofline.board.exceptions.TimeOutException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.Hand;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.energies.exception.DontUsePowerBeforeThirdRound;
import org.springframework.samples.endofline.energies.exception.DontUsePowerInTheSameRound;
import org.springframework.samples.endofline.card.HandService;
import org.springframework.samples.endofline.card.exceptions.OnlyChangeHandOneTime;
import org.springframework.samples.endofline.card.exceptions.PlayCardWhitHandSizeLessThanFive;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.game.exceptions.GameIsFullException;
import org.springframework.samples.endofline.game.exceptions.TwoPlayersAtLeastException;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/games")
public class GameController {

    public static final String GAME_VIEW = "games/gameView";
    public static final String GAME_LIST = "games/gameList";
    public static final String GAME_CREATION = "games/gameCreationForm";
    public static final String GAME_LOBBY = "games/gameLobby";
    public static final String GAME_STATICPOSTGAME = "games/staticPostGame";
    public static final String GAME_LOST = "games/gameLost";


    private GameService gameService;
    private UsuarioService userService;
    private BoardService boardService;
    private EnergyService energyService;
    private PowerService powerService;
    private TurnService turnService;
    private HandService handService;

    

    
    @Autowired
    public GameController(TurnService turnService, EnergyService energyService, PowerService powerService, GameService gameService, UsuarioService userService,BoardService boardService, HandService handService){
        this.gameService = gameService;
        this.userService = userService;
        this.boardService = boardService;
        this.powerService = powerService;
        this.energyService = energyService; 
        this.handService = handService;
        this.turnService = turnService;

    }

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    private Usuario getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return userService.findByUsername(user.getUsername()).orElseThrow(IllegalArgumentException::new);
    }

    @GetMapping
    public String getGames(Model model, HttpServletResponse response) {
        Collection<Game> games = gameService.getVersusGames();
        model.addAttribute("games", games);
        response.addHeader("Refresh", "5");
        return GAME_LIST;
    }
    
    @GetMapping("/currentGame")
    public String getGame(HttpSession session, Model model, HttpServletResponse response) {
        Game game = gameService.getGameByPlayer(getLoggedUser());

        if(game == null) {
            return "redirect:/games";
        }
        
        model.addAttribute("game", game);

        if(session.getAttribute("errorMessage") != null && !session.getAttribute("errorMessage").equals("")){
            model.addAttribute("message", session.getAttribute("errorMessage"));
            session.removeAttribute("errorMessage");
        }
      

        response.addHeader("Refresh", "5");


        
        if(game.getGameState() == GameState.LOBBY){
            model.addAttribute("logged", getLoggedUser().getUsername());
            model.addAttribute("creator", game.getPlayers().get(0).getUsername());
            return GAME_LOBBY;
        }

        if(getLoggedUser().getGameEnded() || game.getGameState() == GameState.ENDED){
            if(game.getGameMode()!= GameMode.VERSUS){
            Integer score = gameService.getScore(game.getPlayers().get(0));
            model.addAttribute("score", score);
            }

            model.addAttribute("userLost", getLoggedUser().getGameEnded());
            return GAME_LOST;
        }
        
        model.addAttribute("board", game.getBoard());

        Deck deck=boardService.deckFromPlayers(getLoggedUser());
        model.addAttribute("hand", boardService.handByDeck(deck));
        if(boardService.handByDeck(deck).getDismissCardsList().size()>=1){
            model.addAttribute("dismiss", boardService.handByDeck(deck).getDismissCardsList().get(boardService.handByDeck(deck).getDismissCardsList().size()-1));
        }
        model.addAttribute("cardTypes",boardService.getAllCardTypes());
        model.addAttribute("colors", Stream.of(CardColor.values()).map(Object::toString).map(String::toLowerCase).collect(Collectors.toList()));
        
        model.addAttribute("user", getLoggedUser());

        model.addAttribute("powers", powerService.getPowerNames());

        model.addAttribute("power",new Power());
        model.addAttribute("logged", getLoggedUser().getUsername());
       
        model.addAttribute("energy", getLoggedUser().getEnergy());

        if(game.getRound().getTurns().size() > 0) {
            model.addAttribute("miTurn", game.getRound().getTurns().get(0).getUsuario().getUsername());
            LocalTime start = LocalTime.ofSecondOfDay(game.getRound().getTurns().get(0).getStartTime());
            LocalTime end = start.plusSeconds(300);
            model.addAttribute("timer", Duration.between(LocalTime.now(), end).toSeconds());
        } else {
            model.addAttribute("miTurn", getLoggedUser().getUsername());
            model.addAttribute("timer", "Indefinido");
        }
        
        return GAME_VIEW;
    }

    @RequestMapping("/newHand")
    public String getRestartHand(HttpSession session, Model model, HttpServletResponse response, HttpServletRequest request){
        if(!request.getMethod().equalsIgnoreCase("post")){
            return "redirect:/games/currentGame";
        }
        Deck deck = boardService.deckFromPlayers(getLoggedUser());
        deck.setCount(deck.getCount()+1);
        
        try{
        handService.generateChangeHand(deck);
        }catch(PlayCardWhitHandSizeLessThanFive v){
        session.setAttribute("errorMessage", "No puedes hacer esto recula");
        return "redirect:/games/currentGame";
        }catch(OnlyChangeHandOneTime c){
            session.setAttribute("errorMessage", "No puedes cambiar de mano mas de una vez");
        return "redirect:/games/currentGame";
        }
        return "redirect:/games/currentGame";
    }

    @PostMapping("/usePower")
    public String usePowerInGame(@RequestParam("name") String powerName,  Model model, HttpServletResponse response, HttpSession session){
        Game game = gameService.getGameByPlayer(getLoggedUser());
        try{
            if(getLoggedUser().equals(game.getRound().getTurns().get(0).getUsuario()) && 
            turnService.getByUsername(getLoggedUser().getUsername()).getCardCounter() == 0){
            energyService.usePower(getLoggedUser(), powerService.findByName(powerName).getId());
            }
        }catch(DontUsePowerInTheSameRound v){
            session.setAttribute("errorMessage", "No puedes usar mas de un punto de energ??a en la misma ronda");
            return "redirect:/games/currentGame";
        }catch(DontUsePowerBeforeThirdRound c){
            session.setAttribute("errorMessage", "No puedes usar poder antes de la ronda 3");
            return "redirect:/games/currentGame";
        }

        return  "redirect:/games/currentGame";
    }

    @PostMapping("/currentGame")
    public String getAction(@RequestParam("x") Integer x, @RequestParam("y") Integer y, @RequestParam("cardId") Card card, Model model, HttpServletResponse response, HttpSession session) {
        System.out.println(gameService.getGameByPlayer(getLoggedUser()).getBoard().getTiles());
        try {
            Tile tile = boardService.tileByCoords(gameService.getGameByPlayer(getLoggedUser()).getBoard(), x, y);
            boardService.playCard(getLoggedUser(),card, tile);
        } catch (InvalidMoveException e) {
            session.setAttribute("errorMessage", "No puedes realizar esa acci??n");
            return "redirect:/games/currentGame";
        } catch (NotUrTurnException n){
            session.setAttribute("errorMessage", "No es tu turno");
            return "redirect:/games/currentGame";
        } catch(TimeOutException t){
            session.setAttribute("errorMessage", "Se acabo el tiempo para realizar el turno");
            return "redirect:/games/currentGame";
        }
        //return getGame(model, response);
        return "redirect:/games/currentGame";
    }

    @GetMapping("/new")
    public String showGameCreationForm(Model model) {

        Game game = new Game();

        List<GameMode> allModes = List.of(GameMode.values());
    
        model.addAttribute("game", game);
        model.addAttribute("modes", allModes);

        return GAME_CREATION;
    }

    @PostMapping("/new")
    public String createGame(@ModelAttribute("game") @Valid Game game, BindingResult result, Model model) throws GameIsFullException {
        if(result.hasErrors()) {
            return GAME_CREATION;
        }

        model.addAttribute("game", game);

        try {
            gameService.createGame(game);
        } catch (DuplicatedGameNameException e) {
            result.rejectValue("name", "duplicate", "Ya existe una sala con ese nombre");
            return GAME_CREATION;
        }

        gameService.joinGame(game, getLoggedUser());
        return "redirect:/games/currentGame";
    }



    @GetMapping("/join/{gameId}")
    public String joinGame(@PathVariable("gameId") Game game, HttpSession session) {
        try {
            gameService.joinGame(game, getLoggedUser());
            return "redirect:/games/currentGame";
        }catch (GameIsFullException e){
            session.setAttribute("errorMessage", "La partida a la que intenta unirse est?? llena");
            return "redirect:/games";
        }
        
    }
    

    @GetMapping("/leave")
    public String leaveGame() {
        gameService.leaveGame(getLoggedUser());
        return "redirect:/games";
    }

    @GetMapping("/{gameId}/start")
    public String startGame(@PathVariable("gameId") Game game, Model model, HttpSession session) {
        System.out.println(game.getPlayers().get(0).getUsername());
        if(game.getPlayers().get(0).equals(getLoggedUser())) {
            try{
                gameService.startGame(game);
                
            }catch(TwoPlayersAtLeastException t){
                String errorMsg = "Para comenzar una partida se necesitan m??nimo 2 jugadores.";
                session.setAttribute("errorMessage", errorMsg);
                return "redirect:/games/currentGame";
            }
        }
       
        return "redirect:/games/currentGame";
    }

    @GetMapping("/listGames/{gameState}")
    public String listGamesByState(@PathVariable("gameState") String gameState, Model model){
        for(Game g: gameService.getGames()){
            if((g.getGameState().toString().toLowerCase()).equals(gameState)){
                List<Game>  pg = gameService.getGameByState(g.getGameState());
                model.addAttribute("games", pg);
            }
        }
    return "games/listGames";
    }

    @PostMapping("/DismissCard")
    public String getActionDismiss(Model model, HttpServletResponse response) {
            Deck deck= boardService.deckFromPlayers(getLoggedUser());
            Hand hand= handService.findHandByDeck(deck);
            handService.dismissCard(hand);
        return "redirect:/games/currentGame";
    }
}
