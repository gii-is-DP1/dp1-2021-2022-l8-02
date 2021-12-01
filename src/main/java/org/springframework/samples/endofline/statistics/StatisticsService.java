package org.springframework.samples.endofline.statistics;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    
    @Autowired
    StatisticsRepository statisticsRepositor;

    GameService gameService;

    UsuarioService usuarioService;

    @Transactional
    public Collection<Statistics> findAll(){
        return statisticsRepositor.findAll();
    }


    @Transactional
    public Statistics findByUser(Usuario usuario){
        return statisticsRepositor.findStatisticsByUser(usuario);
    }

    public void save(Statistics s){
        statisticsRepositor.save(s);
    }

    public void NumOfgames(String username){
        List<Game> games = (List<Game>) gameService.getGames();
        Usuario user = usuarioService.findByUsername(username).get();
        Integer acum = 0;
        for(Game g: games){
            if(g.getPlayers().contains(user)){
                acum += 1;
            }else{
                acum = acum;
            }
        }
        statisticsRepositor.findStatisticsByUser(user).setNumGames(acum);
    }


    // @Transactional
    // public Statistics findOne(String username){
    //     Integer numOfGames = statisticsRepositor.getNumberOfGames(username);

    //     Statistics result = new Statistics();
    //     result.setNumGames(numOfGames);
    //     statisticsRepositor.save(result);

    //     return result;




}

