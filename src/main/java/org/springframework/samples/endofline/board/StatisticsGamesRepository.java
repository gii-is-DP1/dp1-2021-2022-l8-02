package org.springframework.samples.endofline.board;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.usuario.Usuario;

public interface StatisticsGamesRepository extends CrudRepository<StatisticsGames, Integer>{

    public Collection<StatisticsGames> findAll();

    public StatisticsGames findByUserAndGame(Usuario user, Game game);
}
