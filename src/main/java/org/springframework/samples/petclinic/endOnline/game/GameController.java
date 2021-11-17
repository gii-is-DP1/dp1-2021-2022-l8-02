package org.springframework.samples.petclinic.endOnline.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService=gameService;
    }

    @GetMapping("/{gameId}")
    public String findGame(@PathVariable("gameId") int gameId, ModelMap modelMap){
        String direction="/games/listGames";
        Game game=gameService.findGameId(gameId);
        modelMap.addAttribute("game", game);
        return direction;
    }
}
