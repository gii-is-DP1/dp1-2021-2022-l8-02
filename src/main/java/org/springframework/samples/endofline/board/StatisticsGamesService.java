package org.springframework.samples.endofline.board;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.usuario.Usuario;

import org.springframework.stereotype.Service;

@Service
public class StatisticsGamesService {

    @Autowired
    StatisticsGamesRepository statisticsG;

    @Transactional
    public void save(StatisticsGames statisticsGame) {
        statisticsG.save(statisticsGame);
    }

    public Collection<StatisticsGames> findAll() {
        return statisticsG.findAll();
    }

    public StatisticsGames findStatisticsGamesByUserGames(Usuario user, Game game) {
        return statisticsG.findByUserAndGame(user, game);
    }

    public Map<Card, Integer> userMap(Card card, Map<Card, Integer> mapCard) {

        if (mapCard.containsKey(card)) {
            Integer valistic = mapCard.get(card);
            mapCard.put(card, valistic + 1);
        } else {
            mapCard.put(card, 1);
        }
        return mapCard;
    }

    @Transactional
    public void statisticsGamesInitialize(List<Usuario> players, Game game) {
        for (Usuario player : players) {
            Map<Card, Integer> map = new HashMap<>();
            StatisticsGames statisticsGame = new StatisticsGames();
            statisticsGame.setUser(player);
            statisticsGame.setGame(game);
            statisticsGame.setMap(map);
            statisticsGame.setPoint(0);
            save(statisticsGame);
        }
    }

}
