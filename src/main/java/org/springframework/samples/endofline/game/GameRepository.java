package org.springframework.samples.endofline.game;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

    Collection<Game> findAll();

    List<Game> findByGameMode(GameMode gameMode);

    @Query("SELECT game FROM Game game JOIN game.players player WHERE player.username = ?1")
    Game getGameByPlayerUsername(String username);

    Game findByName(String name);

    @Query("SELECT game FROM Game game WHERE game.gameState = ?1")
    List<Game> getGameByGameState(GameState state);
    
}
