package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.energies.Energy;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.power.Power;
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
    EnergyService energyService;

    @Autowired
    GameService gameService;

    @Autowired
    DeckService deckService;

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
    public List<Integer> generateInicialOrderByPlayer(List<Usuario> listUsers){
        List<Integer> listInicialByUser= new ArrayList<>();
        Set<Integer> iniciativeSet= new HashSet<>();
        Random random= new Random();
        for(Usuario user: listUsers){
            Deck deck= deckService.getDeckFromPlayer(user);
            Integer sumCards= deck.getCards().size();
            Integer valRandom= random.nextInt(sumCards-1);
            Card card= deck.getCards().get(valRandom);
            Integer iniciative= card.getCardType().getIniciative();
            user.getInicialListCardsByPlayer().add(iniciative);
            iniciativeSet.add(user.getInicialListCardsByPlayer().get(0));
        }
        listInicialByUser.addAll(iniciativeSet);
        Collections.sort(listInicialByUser);
        return listInicialByUser;
    }

    public Integer minSizeListPlayer(List<Usuario>listPlayers){
        List<Integer> minList= new ArrayList<>();
        for(Usuario user: listPlayers){
                minList.add(user.getInicialListCardsByPlayer().size());
        }
        Integer min= Collections.min(minList);
        return min;
    }

    @Transactional
    public List<Usuario> generateListOrderedWithRepeateIniciative(List<Usuario> listPlayers, List<Integer>listIniciativeOrdered){
    //    Hacer un diccionario en el
    List<Usuario> listPlayerOrder= new ArrayList<>();
    Map<Integer,List<Usuario>> diccNoUser= new HashMap<>();
    List<Usuario> deletePlayerList= new ArrayList<>();
    deletePlayerList.addAll(listPlayers);
    Map<Integer,List<List<Usuario>>> diccForRound= new HashMap<>();
    List<List<Usuario>> listUserOrder= new ArrayList<>();
    Integer val= 1; 
    List<Integer> positionNoList= new ArrayList<>();
    for(Integer creationList=0; creationList<listIniciativeOrdered.get(listIniciativeOrdered.size()-1); creationList++){
        List<Usuario> playerListOrdered= new ArrayList<>();
        listUserOrder.add(playerListOrdered);
    }

    for(Integer position= 0; position<listIniciativeOrdered.get(listIniciativeOrdered.size()-1); position++){
        List<List<Usuario>> newListPlayers= new ArrayList<>();
        diccForRound.put(position,newListPlayers);
        for(Integer creationList=0; creationList<listIniciativeOrdered.get(listIniciativeOrdered.size()-1); creationList++){
            List<Usuario> playerList= new ArrayList<>();
            diccForRound.get(position).add(playerList);
        }
        if(deletePlayerList.size()!=0){
            for(Usuario player:deletePlayerList){
                diccForRound.get(position).get(player.getInicialListCardsByPlayer().get(player.getInicialListCardsByPlayer().size()-val)).add(player);
            }
        }
        Integer ord=0;
        if(diccNoUser.size()==0){
            for(List<Usuario> playerList:diccForRound.get(position)){
                if(playerList.size()==1){
                    listUserOrder.get(ord).add(playerList.get(0));
                    deletePlayerList.remove(playerList.get(0));
                }
                else{
                    diccNoUser.put(ord, playerList);
//                    positionNoList.add();
                }
                ord+=1;
            }
        }
        else{
            for(Integer i: diccNoUser.keySet()){
                List<Usuario> lPlayerDicc=diccNoUser.get(i);
                for(List<Usuario> playerList:diccForRound.get(position)){
                    //Caso en el que sean la misma iniciativa pero de rondas distintas;
                    if(playerList.containsAll(lPlayerDicc)){
                        List<Usuario> checkList= new ArrayList<>();
                        checkList.addAll(playerList);
                        for(Usuario player: playerList){
                            checkList.remove(player);
                        }
                        if(checkList.size()==1){
                            listUserOrder.get(i).add(checkList.get(0));
                            deletePlayerList.remove(checkList.get(0));
                            diccNoUser.get(i).remove(checkList.get(0));
                        }
                    }
                    
                    else{ 
                        if(playerList.size()==1){
                            listUserOrder.get(i).add(playerList.get(0));
                            deletePlayerList.remove(playerList.get(0));
                            diccNoUser.get(i).remove(playerList.get(0));
                            if(diccNoUser.get(i).size()==1){
                                Usuario endPlayer=diccNoUser.get(i).get(0);
                                listUserOrder.get(i).add(endPlayer);
                                deletePlayerList.remove(endPlayer);
                                diccNoUser.get(i).remove(endPlayer);
                            }
                        }
                    }
                }
            }
        }
        val++;
    }
    if(deletePlayerList.size()>0){
        for(Integer m:diccNoUser.keySet()){
            for(Usuario comp: diccNoUser.get(m)){
                for(Usuario playerTest: deletePlayerList){
                    if(comp.equals(playerTest)){
                        listUserOrder.get(m).add(playerTest);
                    }
                }
            }
        }
    }
    
    for(List<Usuario> listForUser: listUserOrder){
        listPlayerOrder.addAll(listForUser);
    }
    
    return listPlayerOrder;
}

    @Transactional
    public List<Usuario> generateOrderListByUser(List<Usuario> listUser){
        Map<Integer,List<Usuario>> repeatingDiccUser= new HashMap<>();       
        List<Usuario> listUserOrder= new ArrayList<>();
        List<Integer> iniciatives= deckService.orderIniciatives();
        Set<Integer> setIniciativesToUser= new HashSet<>();
        for(Integer val: iniciatives){
            for(Usuario user: listUser){
                Integer p=user.getInicialListCardsByPlayer().get(user.getInicialListCardsByPlayer().size()-1);
                if(val==p){
                    if(repeatingDiccUser.containsKey(val)){
                        repeatingDiccUser.get(val).add(user);
                    }
                    else{
                        List<Usuario> newListUsuarios= new ArrayList<>();
                        newListUsuarios.add(user);
                        repeatingDiccUser.put(val,newListUsuarios);
                    }
                    setIniciativesToUser.add(val);

                }
            }
        }
        for(Integer iniciative: setIniciativesToUser){           
            if(repeatingDiccUser.get(iniciative).size()>1){
                List<Usuario> listPlayer= generateListOrderedWithRepeateIniciative(repeatingDiccUser.get(iniciative), iniciatives);
                listUserOrder.addAll(listPlayer);
            }
            else{
                listUserOrder.addAll(repeatingDiccUser.get(iniciative));
            }
        }
        return listUserOrder;
    }

    @Transactional
    public List<Usuario> generateOrderByPlayer(List<Usuario> listUser){
        List<Usuario> list= new ArrayList<>();
        List<Integer> listInteger= new ArrayList<>();
        Set<Integer> setIniciative= new HashSet<>();
        for(Usuario u: listUser){
            setIniciative.add(u.getInicialListCardsByPlayer().get(u.getInicialListCardsByPlayer().size()-1));
        }
        if(setIniciative.size()<listUser.size()){
            List<Usuario> orderList=generateOrderListByUser(listUser);
            list.addAll(orderList);
        }
        else{
            for(Integer i: setIniciative){
                listInteger.add(i);
            }
            Collections.sort(listInteger);
            for(Integer orderIniciative: listInteger){
                for(Usuario player: listUser){
                    if(player.getInicialListCardsByPlayer().get(player.getInicialListCardsByPlayer().size()-1)==orderIniciative){
                        list.add(player);
                    }
                }
            }
        }
        return list;
    }

    @Transactional
    public void generateTurnsByPlayers(Round round, int numPlayers){
        List<Turn> turns = new ArrayList<>();
        if(round.getNumber()>1){
            List<Usuario> listUser= generateOrderByPlayer(round.getPlayers());
            round.setPlayers(listUser);
            save(round);
        }    
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
        List<Turn> previousTurns = round.getTurns();
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
        Map<Power, Boolean> map = player.getEnergy().getPowers();
        Set<Power> powers = map.keySet();
        for(Power p: powers){
           map.put(p, false);
        }
        Energy ene = player.getEnergy();
        ene.setPowers(map);
        player.setEnergy(ene);
        energyService.save(ene);
        List<Turn> turns = new ArrayList<>(game.getRound().getTurns());
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        turns.remove(turnService.getByUsername(player.getUsername()));
        turnService.delete(turnService.getByUsername(player.getUsername()));
        game.getRound().setTurns(turns);
        if(game.getRound().getTurns().size() == 0){
            Round round = game.getRound();
            round.setNumber(game.getRound().getNumber()+1);
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
