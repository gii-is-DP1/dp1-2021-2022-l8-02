package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Tile;
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
        round.setTurns(turns);
        save(round);
    }

    public List<Turn> calculateNextRoundTurns(Round round, int numPlayers){
        List<Turn> turns = new ArrayList<>();
        List<Integer> inics = new ArrayList<>();
        if(round.getId()==1){
            List<Integer> ls = new ArrayList<>();
            for(int i = 0;i<numPlayers;i++) ls.add(i);
            Collections.shuffle(ls);
            for(Integer e:ls){
                Turn turn = new Turn();
                turn.setRound(round);
                turn.setUsuario(round.getPlayers().get(e));
                turnService.save(turn);
                turns.add(turn);
                round.getPlayers().get(e).setTurn(turn);
                usuarioService.save(round.getPlayers().get(e));
            }
        }else{
            for(int i = 0;i < numPlayers;i++){
                List<Tile> ocTiles = round.getGame().getBoard().getPaths().get(i).getOccupiedTiles();//This could be stored maybe to gain efficiency
                Integer inic = ocTiles.get(ocTiles.size()-1).getCard().getCardType().getIniciative();
                inics.add(inic);
            }
        }return turns;
    }

    @Transactional
    public void refreshRound(Game game, Usuario player){
        List<Turn> turns = new ArrayList<>(game.getRound().getTurns());
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        turns.remove(turnService.getByUsername(player.getUsername()));
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
