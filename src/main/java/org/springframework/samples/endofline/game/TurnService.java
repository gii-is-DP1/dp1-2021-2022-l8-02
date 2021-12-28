package org.springframework.samples.endofline.game;

import java.util.Collection;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.board.exceptions.NotUrTurnException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    @Autowired
    TurnRepository turnRepository;
    @Autowired
    RoundService roundService;
    @Autowired
    PowerService powerService;
    
    private BoardService boardService;

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

    public void cardCounter(Usuario player, Game game, Map<Power, Boolean> powers){
        Integer count = getByUsername(player.getUsername()).getCardCounter();
        count+=1;
        Turn t = player.getTurn();
        t.setCardCounter(count);
        save(t);
        player.setTurn(t);
        if(player.getTurn().getRound().getId() == 1 || powers.get(powerService.findById(2)).booleanValue() == true){
            roundService.refreshRound(game, player);
        }else if(powers.get(powerService.findById(1)).booleanValue() == true){
            if(player.getTurn().getCardCounter() == 3){
                roundService.refreshRound(game, player);
            }
        }
        else if (player.getTurn().getRound().getId() >= 2){
            if(player.getTurn().getCardCounter() == 2){
                roundService.refreshRound(game, player);
            }
        }
        
    }
}
