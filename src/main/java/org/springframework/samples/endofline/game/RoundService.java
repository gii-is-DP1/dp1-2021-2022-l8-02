package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
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

    @Autowired
    BoardService boardService;

    @Autowired
    GameService gameService;

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
    public void generateTurnsByPlayers(Round round, List<Usuario> numPlayers){
        List<Turn> turns = new ArrayList<>();
        for(int i = 0; i < numPlayers.size(); i++){
            if(!numPlayers.get(i).getGameEnded()){
                Turn turn = new Turn();
                turn.setRound(round);
                turn.setUsuario(round.getPlayers().get(i));
                turnService.save(turn);
                turns.add(turn);
                round.getPlayers().get(i).setTurn(turn);
            }
        }
        if(turns.size() > 0){
            turns.get(0).setStartTime(boardService.hourToInteger());
            turnService.save(turns.get(0));
            round.setTurns(turns);
            save(round);
        }
    }

    @Transactional
    public void refreshRound(Game game, Usuario player, List<Tile> availableTiles){
        List<Turn> turns = new ArrayList<>(game.getRound().getTurns());
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        if(players.size() == 2){
            if(gameService.checkDrawVS(game, availableTiles)){
                gameService.endGame(game);
            }else{
                if(gameService.checkLostVS(game, availableTiles).size() > 0){
                    gameService.checkLostVS(game, availableTiles).get(0).setGameEnded(true); //poner aqui fin de partida con pantalla de jugador x
                    usuarioService.save(gameService.checkLostVS(game, availableTiles).get(0));
                    players.remove(gameService.checkLostVS(game, availableTiles).get(0));
                    //poner aqui que el otro jugador ha ganado
                }
            }
        }else if(players.size() > 2){
            if(gameService.checkLostVS(game, availableTiles).size() > 0){
                for(int i = 0; i < gameService.checkLostVS(game, availableTiles).size(); i++){
                    gameService.checkLostVS(game, availableTiles).get(i).setGameEnded(true);
                    usuarioService.save(gameService.checkLostVS(game, availableTiles).get(i));
                    players.remove(gameService.checkLostVS(game, availableTiles).get(i));
                    System.out.println("se acabo el game para jugador x"); //poner aqui el fin de partida con pantalla de jugador x ha perdido (todo para cada jugador que pierde)
                }
            }
        }
        turns.remove(turnService.getByUsername(player.getUsername()));
        if(turns.size() > 0){
            turns.get(0).setStartTime(boardService.hourToInteger());
            turnService.save(turns.get(0));
        }
        turnService.delete(turnService.getByUsername(player.getUsername()));
        game.getRound().setTurns(turns);
        if(game.getRound().getTurns().size() == 0){
            delete(game.getRound());
            Round round = new Round();
            round.setGame(game);
            round.setPlayers(players);
            save(round);
            generateTurnsByPlayers(round, game.getPlayers());
            save(round);
            game.setRound(round);
        }
        save(game.getRound());
    }
}
