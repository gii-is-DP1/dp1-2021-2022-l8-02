package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.energies.Energy;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.card.HandService;
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

    @Autowired
    EnergyService energyService;

    @Autowired
    PowerService powerService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private HandService handService;

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
    public void refreshRound(Game game, Usuario player, Card card){
        List<Turn> turns = new ArrayList<>(game.getRound().getTurns());
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        if(players.size() == 2){
            if(gameService.checkDrawVS(game)){
                gameService.checkLostVS(game).get(0).setGameEnded(true);
                gameService.checkLostVS(game).get(1).setGameEnded(true);
                usuarioService.save(gameService.checkLostVS(game).get(0));
                usuarioService.save(gameService.checkLostVS(game).get(1));
                gameService.endGame(game);
            }else{
                if(gameService.checkLostVS(game).size() > 0){
                    gameService.checkLostVS(game).get(0).setGameEnded(true); //poner aqui fin de partida con pantalla de jugador x
                    usuarioService.save(gameService.checkLostVS(game).get(0));
                    players.remove(gameService.checkLostVS(game).get(0));
                    gameService.endGame(game);
                }
            }
        }else if(players.size() > 2){
            if(gameService.checkLostVS(game).size() > 0){
                for(int i = 0; i < gameService.checkLostVS(game).size(); i++){
                    gameService.checkLostVS(game).get(i).setGameEnded(true);
                    usuarioService.save(gameService.checkLostVS(game).get(i));
                    players.remove(gameService.checkLostVS(game).get(i));
                }
            }
        }
        Integer count = turnService.getByUsername(player.getUsername()).getCardCounter();
        count += 1;
        Turn t = player.getTurn();
        t.setCardCounter(count);
        turnService.save(t);
        if(game.getRound().getNumber() >= 2){
            if(player.getEnergy().getPowers().get(powerService.findById(2)).booleanValue()){
                    Map<Power, Boolean> map = player.getEnergy().getPowers();
                    Set<Power> powers = map.keySet();
                    for(Power p: powers){
                    map.put(p, false);
                    }
                    Energy ene = player.getEnergy();
                    ene.setPowers(map);
                    player.setEnergy(ene);
                    energyService.save(ene);
                
            }else if(player.getEnergy().getPowers().get(powerService.findById(4)).booleanValue()){
                handService.generateDefaultHand(deckService.getDeckFromPlayer(player));
                Map<Power, Boolean> map = player.getEnergy().getPowers();
                Set<Power> powers = map.keySet();
                for(Power p: powers){
                map.put(p, false);
                }
                Energy ene = player.getEnergy();
                ene.setPowers(map);
                player.setEnergy(ene);
                energyService.save(ene);
                return;
            }else if(player.getTurn().getCardCounter() <= 2 && player.getEnergy().getPowers().get(powerService.findById(1)).booleanValue()){
                if(player.getTurn().getCardCounter()<3){
                    return;
                }else if (player.getTurn().getCardCounter()==3){
                    Map<Power, Boolean> map = player.getEnergy().getPowers();
                    Set<Power> powers = map.keySet();
                    for(Power p: powers){
                    map.put(p, false);
                    }
                    Energy ene = player.getEnergy();
                    ene.setPowers(map);
                    player.setEnergy(ene);
                    energyService.save(ene);
                }
            }else if(player.getTurn().getCardCounter() < 2){
                return;
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
            for(Usuario p : game.getPlayers()){
            handService.generateDefaultHand(deckService.getDeckFromPlayer(p));
            }
            Round round = game.getRound();
            round.setNumber(game.getRound().getNumber()+1);
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
