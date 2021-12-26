package org.springframework.samples.endofline.game;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    @Autowired
    TurnRepository turnRepository;

    public Collection<Turn> getTurns(){
        return turnRepository.findAll();
    }

    @Transactional
    public void save(Turn turn){
        turnRepository.save(turn);
    }

    @Transactional
    public void delete(Turn turn){
        turnRepository.delete(turn);
    }

    public Turn getByUsername(String username){
        return turnRepository.getTurnByUsername(username);
    }
}
