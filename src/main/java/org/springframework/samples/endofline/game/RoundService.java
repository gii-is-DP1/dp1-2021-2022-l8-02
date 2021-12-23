package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class RoundService {

    @Autowired
    RoundRepository roundRepository;

    @Autowired
    TurnService turnService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    BoardService boardSercive;

    public Collection<Round> getRounds(){
        return roundRepository.findAll();
    }

    @Transactional
    public void save(Round round){
        roundRepository.save(round);
    }

    public void delete(Round round){
        roundRepository.delete(round);
    }

    @Transactional
    public void copyRound(Round round1, Round round2){
        round2.setGame(round1.getGame());
        round2.setPlayers(round1.getPlayers());
        save(round2);
    }

    @Transactional
    public void generateTurnsByPlayers(Round round, int numPlayers){
        List<Turn> turns = new ArrayList<>();
        for(int i = 0; i < numPlayers; i++){
            Turn turn = new Turn();
            turn.setRound(round);
            turn.setUsuario(round.getPlayers().get(i));
            turnService.save(turn);
            turns.add(turn);
            round.getPlayers().get(i).setTurn(turn);
            usuarioService.save(round.getPlayers().get(i));
        }
        turns.get(0).setStartTime(boardSercive.hourToInteger());
        turnService.save(turns.get(0));
        round.setTurns(turns);
        save(round);
    }

    @Transactional
    public void refreshRound(Game game, Usuario player){
        List<Turn> turns = new ArrayList<>(game.getRound().getTurns());
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        turns.remove(turnService.getByUsername(player.getUsername()));
        turns.get(0).setStartTime(boardSercive.hourToInteger());
        turnService.save(turns.get(0));
        turnService.delete(turnService.getByUsername(player.getUsername()));
        game.getRound().setTurns(turns);
        if(game.getRound().getTurns().size() == 0){
            delete(game.getRound());
            Round round = new Round();
            round.setGame(game);
            round.setPlayers(players);
            save(round);
            generateTurnsByPlayers(round, game.getPlayers().size());
            save(round);
            game.setRound(round);
        }
        save(game.getRound());
    }
}
