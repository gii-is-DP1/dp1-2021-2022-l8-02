package org.springframework.samples.petclinic.endofline.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.endofline.board.BoardUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {

    public static final String GAME_VIEW = "games/gameView";

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    
    @GetMapping("/{gameId}")
    public String getGame(@PathVariable("gameId") Integer gameId, Model model) {
        // Game game = gameService.findGame(gameId);
        // model.addAttribute("board", game.getBoard());
        model.addAttribute("board", BoardUtils.emptyBoard(5));
        return GAME_VIEW;
    }

}
