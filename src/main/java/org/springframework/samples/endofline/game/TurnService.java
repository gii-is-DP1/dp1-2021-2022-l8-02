package org.springframework.samples.endofline.game;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    @Autowired
    TurnRepository turnRepository;

    public Collection<Turn> getTurns(){
        return turnRepository.findAll();
    }

    public void save(Turn turn){
        turnRepository.save(turn);
    }

    public Turn getByUsername(String username){
        return turnRepository.getTurnByUsername(username);
    }
}
