package org.springframework.samples.endofline.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.StatisticsGames;
import org.springframework.samples.endofline.board.StatisticsGamesService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.TileService;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    private CardService cardService;
    private DeckService deckService;
    private BoardService boardService;
    private TileService tileService;
    private StatisticsGamesService statisticsGamesService;

    @Autowired
    public GameController(GameService gameService, UsuarioService userService, CardService cardService, DeckService deckService,BoardService boardService, TileService tileService, StatisticsGamesService statisticsGamesService){
        this.gameService = gameService;
        this.userService = userService;
        this.cardService = cardService;
        this.deckService = deckService;
        this.boardService = boardService;
        this.tileService = tileService;
        this.statisticsGamesService= statisticsGamesService;
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
        // Me gustaria hacer el sistema de pageable aqui :D
        Collection<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return GAME_LIST;
    }
    
    @GetMapping("/currentGame")
    public String getGame(Model model) {
        Game game = gameService.getGameByPlayer(getLoggedUser());

        if(game == null) {
            return GAME_LIST;
        }

        model.addAttribute("game", game);
        System.out.println(game.getGameState());
        
        if(game.getGameState() == GameState.LOBBY)  return GAME_LOBBY;
        
        model.addAttribute("board", game.getBoard());
        model.addAttribute("deck", deckService.getDeckFromPlayer(getLoggedUser()));

        // For rendering card images
        model.addAttribute("cardTypes", cardService.findAllCardTypes());
        model.addAttribute("colors", Stream.of(CardColor.values()).map(Object::toString).map(String::toLowerCase).collect(Collectors.toList()));

        model.addAttribute("user", getLoggedUser());

        return GAME_VIEW;
    }

    @PostMapping("/currentGame")
    public String getAction(@RequestParam("x") Integer x, @RequestParam("y") Integer y, @RequestParam("cardId") Card card, Model model) {

        try {
            Tile tile = tileService.findTileByCoordsAndBoard(gameService.getGameByPlayer(getLoggedUser()).getBoard(), x, y);
            boardService.playCard(getLoggedUser() ,card, tile);
        } catch (InvalidMoveException e) {
            model.addAttribute("message", "No puedes realizar esa acción"); // Esto no se muestra si se hace un redirect
        }
        return getGame(model); // No se si esto es correcto o una buena forma de hacerlo
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
        for(Usuario player:game.getPlayers()){
            Map<Card, Integer> map= new HashMap<>();
            StatisticsGames statisticsGame= new StatisticsGames();
             statisticsGame.setUser(player);
             statisticsGame.setGame(game);
             statisticsGame.setMap(map);
             statisticsGame.setPoint(0);
             statisticsGamesService.save(statisticsGame);
        }

        if(game.getPlayers().get(0).equals(getLoggedUser()))
            gameService.startGame(game);
        return "redirect:/games/currentGame";
    }

}
