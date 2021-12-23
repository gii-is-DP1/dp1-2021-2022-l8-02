package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.StatisticsGamesService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.board.exceptions.NotUrTurnException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.energies.Energy;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.energies.Powers;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.power.powerService;
import org.springframework.samples.endofline.statistics.Statistics;
import org.springframework.samples.endofline.statistics.StatisticsService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.samples.endofline.power.Power;
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


    private GameService gameService;
    private UsuarioService userService;
    private BoardService boardService;
    private StatisticsGamesService statisticsGamesService;
    private StatisticsService statisticsService;
    private EnergyService energyService;
    private powerService powerService;
    
    @Autowired
    public GameController(GameService gameService, UsuarioService userService,BoardService boardService, StatisticsGamesService statisticsGamesService, StatisticsService statisticsService){
        this.gameService = gameService;
        this.userService = userService;
        this.boardService = boardService;
        this.statisticsGamesService = statisticsGamesService;
        this.statisticsService = statisticsService;
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
    public String getGames(Model model) {
        Collection<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return GAME_LIST;
    }
    
    @GetMapping("/currentGame")
    public String getGame(Model model, HttpServletResponse response) {
        Game game = gameService.getGameByPlayer(getLoggedUser());

        if(game == null) {
            return GAME_LIST;
        }

        model.addAttribute("game", game);
        
        if(game.getGameState() == GameState.LOBBY)  return GAME_LOBBY;
        response.addHeader("Refresh", "5");
        
        model.addAttribute("board", game.getBoard());
        Deck deck=boardService.deckFromPlayers(getLoggedUser());
        model.addAttribute("hand", boardService.handByDeck(deck));
        model.addAttribute("cardTypes",boardService.getAllCardTypes());
        model.addAttribute("colors", Stream.of(CardColor.values()).map(Object::toString).map(String::toLowerCase).collect(Collectors.toList()));
        model.addAttribute("user", getLoggedUser());

        List<Power> allPowers = powerService.findAll();
        List<String> PowersName = new ArrayList<>();
        for(Power p: allPowers){
            String name = p.getName();
            PowersName.add(name);
        }
        model.addAttribute("powers", PowersName);

        model.addAttribute("power",new Power());

        return GAME_VIEW;
    }

    @PostMapping("/currentGame")
    public String getAction(@RequestParam("x") Integer x, @RequestParam("y") Integer y, @RequestParam("cardId") Card card, Model model, HttpServletResponse response) {

        try {
            Tile tile = boardService.tileByCoords(gameService.getGameByPlayer(getLoggedUser()).getBoard(), x, y);
            boardService.playCard(getLoggedUser() ,card, tile);
        } catch (InvalidMoveException e) {
            model.addAttribute("message", "No puedes realizar esa acción");
        } catch (NotUrTurnException n){
            model.addAttribute("message", "No es tu turno");
        }
        return getGame(model, response);
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
    public String createGame(@ModelAttribute("game") @Valid Game game, BindingResult result, Model model) {
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
    public String joinGame(@PathVariable("gameId") Game game) {
        gameService.joinGame(game, getLoggedUser());
        return "redirect:/games/currentGame";
    }
    

    @GetMapping("/leave")
    public String leaveGame() {
        gameService.leaveGame(getLoggedUser());
        return "redirect:/games";
    }

    @GetMapping("/{gameId}/start")
    public String startGame(@PathVariable("gameId") Game game, Model model) {
        // Cambiar a POST puede ser una mejor opcion
        statisticsGamesService.statisticsGamesInitialize(game.getPlayers(), game);

        Statistics s = statisticsService.findByUser(getLoggedUser());
        s.setNumGames(s.getNumGames()+1);
        s.setNumPlayers(game.getPlayers().size());
        statisticsService.save(s);
        
        /*energyService.initEnergy(getLoggedUser());*/
        
        

        if(game.getPlayers().get(0).equals(getLoggedUser())) 
            gameService.startGame(game);
        return "redirect:/games/currentGame";
    }

}
