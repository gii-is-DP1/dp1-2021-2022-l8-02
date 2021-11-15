package org.springframework.samples.petclinic.endofline.game;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.endofline.board.BoardUtils;
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

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String getGames(Model model) {
        // Me gustaria hacer un pageable aqui :D
        // Desde luego el que vea mi codigo con los comentarios solo puede reirse
        Collection<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return GAME_LIST;
    }
    
    @GetMapping("/{gameId}")
    public String getGame(@PathVariable("gameId") Integer gameId, Model model) {
        // Game game = gameService.findGame(gameId);
        // model.addAttribute("board", game.getBoard());
        model.addAttribute("board", BoardUtils.emptyBoard(5));
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
    public String createGame(@Valid Game game, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("haha", "haha"); // Crear sistema de manejo de errores
            return GAME_CREATION;
        }

        gameService.createGame(game);

        return "redirect:/"; // Bup Hacer que te lleve a la sala :D
    }

}
