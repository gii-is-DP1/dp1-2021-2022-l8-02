package org.springframework.samples.endofline.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.usuario.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StatisticsGamesController {

    public static final String STATISTICSGAME= "/games/staticPostGame";
    
    @Autowired
    StatisticsGamesService statisticsGamesService;

    @Autowired
    UsuarioService userService;

    @Autowired
    GameService gameService;

    @GetMapping("/statisticsGame/{gameId}/{userName}")
    public String findStatisticsGameByUser(@PathVariable("gameId") Integer gameId, @PathVariable("userName") String userName, ModelMap model){
        
        StatisticsGames statisticsGames= statisticsGamesService.findStatisticsGamesByUserGames(userService.findByUsername(userName).get(), gameService.findGame(gameId));
        model.addAttribute("statistiscPostGame",statisticsGames);
        return STATISTICSGAME;
    }
}