package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoundService {

    @Autowired
    RoundRepository roundRepository;

    @Autowired
    TurnService turnService;

    public Collection<Round> getRounds(){
        return roundRepository.findAll();
    }

    public void save(Round round){
        roundRepository.save(round);
    }

    public void delete(Round round){
        roundRepository.delete(round);
    }

    public void copyRound(Round round1, Round round2){
        round2.setGame(round1.getGame());
        round2.setPlayers(round1.getPlayers());
        save(round2);
    }

    public void generateTurnsByPlayers(Round round, int numPlayers){
        List<Turn> turns = new ArrayList<>();
        for(int i = 0; i < numPlayers; i++){
            Turn turn = new Turn();
            turn.setRound(round);
            turn.setUsuario(round.getPlayers().get(i));
            turnService.save(turn);
            turns.add(turn);
        }
        round.setTurns(turns);
        save(round);
    }
}
