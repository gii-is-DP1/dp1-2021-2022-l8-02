package org.springframework.samples.petclinic.endofline.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Collection<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game findGame(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()) {
            return game.get();
        }
        throw new IllegalArgumentException();
    }

    public void createGame(Game game) {
        gameRepository.save(game);
    }
    
}
