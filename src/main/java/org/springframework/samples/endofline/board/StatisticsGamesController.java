package org.springframework.samples.endofline.board;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
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
    // @GetMapping("/staticsGames")
    // public String findAll(ModelMap modelMap){
    //     Collection<StatisticsGames> collec= statisticsGamesService.findAll();
    //     modelMap.addAllAttributes(collec);
    //     return STATISTICSGAME;
    // }

    @GetMapping("/statisticsGame/{gameId}/{userName}")
    public String findStatisticsGameByUser(@PathVariable("gameId") Integer gameId, @PathVariable("userName") String userName, ModelMap model){
        
        StatisticsGames statisticsGames= statisticsGamesService.findStatisticsGamesByUserGames(userService.findByUsername(userName).get(), gameService.findGame(gameId));
        model.addAttribute("statistiscPostGame",statisticsGames);
        return STATISTICSGAME;
    }
}