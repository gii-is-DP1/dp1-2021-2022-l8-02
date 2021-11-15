package org.springframework.samples.petclinic.endofline.game;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {

    public static final String GAME_VIEW = "games/gameView";
    public static final String GAME_LIST = "games/gameList";
    public static final String GAME_CREATION = "games/gameCreationForm";
    public static final String GAME_LOBBY = "games/gameLobby";

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String getGames(Model model) {
        // Me gustaria hacer el sistema de pageable aqui :D
        Collection<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return GAME_LIST;
    }
    
    @GetMapping("/{gameId}")
    public String getGame(@PathVariable("gameId") Integer gameId, Model model) {
        Game game = gameService.findGame(gameId);
        model.addAttribute("game", game);
        System.out.println(game.getGameState());
        
        if(game.getGameState() == GameState.LOBBY)  return GAME_LOBBY;
        
        model.addAttribute("board", game.getBoard());
        return GAME_VIEW;
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
    public String createGame(@Valid Game game, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return GAME_CREATION;
        }

        gameService.createGame(game);

        return "redirect:/games";
    }

    @GetMapping("/{gameId}/start")
    public String startGame(@PathVariable("gameId") Integer gameId, Model model) {
        // Cambiar a POST puede ser una mejor opcion
        gameService.startGame(gameId);
        return "redirect:/games/"+gameId;
    }

}
