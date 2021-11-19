package org.springframework.samples.petclinic.endOnline.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepo){
        this.gameRepository= gameRepo;
    }

    @Transactional
    public Collection<Game> findAll(){
        return gameRepository.findAll();
    }

    @Transactional
    public Game findGameId(int id){
        return gameRepository.findGameById(id).get();
    }

    @Transactional
    public Game findGameName(String name){
        return gameRepository.findGameByName(name).get();
    }

    @Transactional
    public void saveGame(Game game){
        gameRepository.save(game);
    } 

    @Transactional
    public void deleteGameById(Game game){
        gameRepository.deleteById(game.getId());
    }
}
