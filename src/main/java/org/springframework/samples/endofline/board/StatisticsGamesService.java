package org.springframework.samples.endofline.board;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class StatisticsGamesService {
    @Autowired
    StatisticsGamesRepository statisticsG;

    public void save( StatisticsGames statisticsGame){
        statisticsG.save(statisticsGame);
    }

    public Collection<StatisticsGames> findAll(){
        return statisticsG.findAll();
    }

    public StatisticsGames findStatisticsGamesByUserGames(Usuario user,Game game){
        return statisticsG.findByUserAndGame(user, game);
    }
    
    public StatisticsGames newStatisticsGames(Game game){
    
        return new StatisticsGames();//;
    }
    
    public Map<Card, Integer> userMap(Card card, Map<Card, Integer> mapCard){

        if(mapCard.containsKey(card)){
            Integer valistic= mapCard.get(card);
            mapCard.put(card, valistic+1);
        }
        else{
            mapCard.put(card, 1);
        }
        return mapCard;
    }
    
}
