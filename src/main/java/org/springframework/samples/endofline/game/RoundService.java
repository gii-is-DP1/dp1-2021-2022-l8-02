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
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.energies.Energy;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
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
    Map<Integer,List<Usuario>> diccNoUser= new HashMap<>();  //DICCIONARIO DONDE EL NÚMERO INDICA LA INICIATIVA CON LOS USUARIOS QUE TIENEN LA INICIATIVA REPETIDA
    List<Usuario> deletePlayerList= new ArrayList<>();
    deletePlayerList.addAll(listPlayers);   //LISTA DE USUARIOS QUE TIENEN LA MISMA INICIATIVA 
    Map<Integer,List<List<Usuario>>> diccForRound= new HashMap<>();  //KEY: INDICA EL ORDEN EN QUE SE IRÁ RECORRIENDO CON EL BUCLE FOR
    List<List<Usuario>> listUserOrder= new ArrayList<>();  //LISTA DE LISTA DE USUARIOS DONDE SE METERÁN LOS USUARIOS CONFORME LA INICIATVA SACADA
    Integer val= 1; 
    for(Integer creationList=0; creationList<listIniciativeOrdered.get(listIniciativeOrdered.size()-1); creationList++){  //PARA INICIALIZAR LA LISTA DE LISTAS DE USUARIOS DEL DICCIONARIO
        List<Usuario> playerListOrdered= new ArrayList<>();
        listUserOrder.add(playerListOrdered);
    }

    //*EL BUCLE FOR RECORRE HASTA LA INICIATVA 5*////SOLUCIÓN, COJER EL JUGADOR CON LA LISTA DE CARTAS JUGADAS MAS GRANDE//
    for(Integer position= 0; position<listIniciativeOrdered.get(listIniciativeOrdered.size()-1); position++){ //INDICA EL ORDEN EN QUE SE VA A IR RECORRIENDO LAS ITERACIONES
        List<List<Usuario>> newListPlayers= new ArrayList<>();
        diccForRound.put(position,newListPlayers);    //INICIALIZA LA LISTA DE LISTAS DE USUARIOS
        for(Integer creationList=0; creationList<listIniciativeOrdered.get(listIniciativeOrdered.size()-1); creationList++){ 
            List<Usuario> playerList= new ArrayList<>();         //CREO UNA LISTA DE USUARIOS PARA LA LISTA DE LISTA DE USUARIOS
            diccForRound.get(position).add(playerList);
        }
        if(deletePlayerList.size()!=0){
            List<Usuario> listPlayerSizeMoreThanVal = new ArrayList<>();  //LISTA PARA LOS JUGADORES CULLA LISTA DE INICIATIVAS COMPARANDO YA A LLEGADO HA 0 
            for(Usuario player:deletePlayerList){  //RECORREMOS LA LISTA DE JUGADORES
                if(player.getInicialListCardsByPlayer().size()-val>=0){   //VAMOS RECORRIENDO LA INICIATIVA DE LOS USUARIOS
                diccForRound.get(position).get(player.getInicialListCardsByPlayer().get(player.getInicialListCardsByPlayer().size()-val)).add(player);//METO A LOS USUARIOS CON LA MISMA INICIATIVA EN EL DICCIONARIO
                }
                else{
                    listPlayerSizeMoreThanVal.add(player);  //LOS JUGADORES CON SU INICIATIVA A 0
                    
                }
            }
            if(listPlayerSizeMoreThanVal.size()>0){
                listPlayerOrder.addAll(listPlayerSizeMoreThanVal);  //LOS JUGADORES ENTRAN EN LA LISTA DE ORDENADOS
                deletePlayerList.removeAll(listPlayerSizeMoreThanVal);  //REMOVEMOS LOS JUGADORES
            }
        }
        
        Integer ord=0;  //INDICA LA INICIATIVA COMO KEY DEL DICCIONARIO DICCNOUSER
        if(diccNoUser.size()==0){  //CREO EL PRIMER DICCIONARIO CON LA INICIATIVA Y ASOCIANDOLOS A UNA LISTA DE USUARIOS REPETIDOS
            for(List<Usuario> playerList:diccForRound.get(position)){   //RECORREMOS LOS JUGADORES DEL DICCIONARIO
                if(playerList.size()==1){   //SI SU TAMAÑO ES 1 SIGNIFACA QUE EL JUGADOR ESTA ORDENADO
                    listUserOrder.get(ord).add(playerList.get(0));   //METO EL USUARIO CON INICIATIVAS DISTINTAS
                    deletePlayerList.remove(playerList.get(0));    //BORRA A LOS USUARIOS DE LA LISTA DE USUARIOS SIN ORDENAR
                }
                else{
                    diccNoUser.put(ord, playerList);  //METO EN UN DICCIONARIO LOS USUARIOS CON LAS INICIATIVAS AUN REPETIDAS
                }
                ord+=1;
            }
        }
        else{
            if(deletePlayerList.size()!=0){       //TAMAÑO DE LOS USUARIOS
            for(Integer i: diccNoUser.keySet()){    //INICIATIVA DE LOS USUARIOS REPETIDOS 
                List<Usuario> lPlayerDicc=diccNoUser.get(i); //Usuarios con iniciativa repetida
                for(List<Usuario> playerList:diccForRound.get(position)){    
                    //Caso en el que sean la misma iniciativa pero de rondas distintas;
                    if(playerList.containsAll(lPlayerDicc)){  
                        List<Usuario> checkList= new ArrayList<>();
                        checkList.addAll(playerList);
                        for(Usuario player: playerList){ //Descubro si queda algún usuario algún usuario al que hay que borrar porque ya estaba en la lista de antes
                            checkList.remove(player);   
                        }
                        if(checkList.size()==1){     //USUARIO SOBRANTE == 0
                            listUserOrder.get(i).add(checkList.get(0));
                            deletePlayerList.remove(checkList.get(0));
                            diccNoUser.get(i).remove(checkList.get(0));
                        }
                        
                    }
                    else{
                        if(playerList.size()==1){       //USUARIOS, QUE TENGA EN LA LISTA DE USUARIOS UN JUGADOR SIGNIFICA QUE YA ESTÁN ORDENADOS
                            listUserOrder.get(i).add(playerList.get(0));
                            deletePlayerList.remove(playerList.get(0));
                            diccNoUser.get(i).remove(playerList.get(0));
                            if(diccNoUser.get(i).size()==1){         //CREO QUE NO HACE FALTA PORQUE YA ES MIRÁ ANTES CON EL PLAYERDICC 
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
    }
    
    val++;
    }
    if(deletePlayerList.size()>0){    //ESTO SE HACE EN UN CASO EXTREMO EN QUE UN USUARIO SALGA DEL FOR PORQUE UN USUARIO A USADO PODER
        for(Integer m:diccNoUser.keySet()){
            for(Usuario comp: diccNoUser.get(m)){
                for(Usuario playerTest: deletePlayerList){
                    if(comp.equals(playerTest)){
                        listUserOrder.get(m).add(playerTest);  //METEMOS DE FORMA ALEATORIA EN LA COMPARACIÓN DE 2 O MÁS USUARIOS LA LISTA DE LISTUSERORDER
                    }
                }
            }
        }
    }
    
    for(List<Usuario> listForUser: listUserOrder){  //ENTREMOS TODAS LAS CARTAS EN LALISTA ORDENADA
        listPlayerOrder.addAll(listForUser);
    }
    
    return listPlayerOrder;
}

    @Transactional
    public List<Usuario> generateOrderListByUser(List<Usuario> listUser){
        Map<Integer,List<Usuario>> repeatingDiccUser= new HashMap<>();    //Key:iniciativa, Entry:lista de los usuarios con esa iniciativa 
        List<Usuario> listUserOrder= new ArrayList<>();  //Lista de usuario
        List<Integer> iniciatives= deckService.orderIniciatives();  //todas las iniciativas de la partida
        Set<Integer> setIniciativesToUser= new HashSet<>();    //Iniciativa de los usuarios
        for(Integer val: iniciatives){    //recorro las iniciativas
            for(Usuario user: listUser){    //Lista de usuarios que le pasamos
                Integer p=user.getInicialListCardsByPlayer().get(user.getInicialListCardsByPlayer().size()-1);   //Última iniciativa de los usuarios
                if(val==p){  //Comparamos las inicitavas de los usuarios
                    if(repeatingDiccUser.containsKey(val)){
                        repeatingDiccUser.get(val).add(user);
                    }
                    else{
                        List<Usuario> newListUsuarios= new ArrayList<>();
                        newListUsuarios.add(user);
                        repeatingDiccUser.put(val,newListUsuarios);
                    }
                    setIniciativesToUser.add(val);   //Conjunto con la iniciativa de los usuarios;

                }
            }
        }
        for(Integer iniciative: setIniciativesToUser){       //Conjunto con la iniciativa
            if(repeatingDiccUser.get(iniciative).size()>1){    //Saca los jugadores del diccionario
                List<Usuario> listPlayer= generateListOrderedWithRepeateIniciative(repeatingDiccUser.get(iniciative), iniciatives);   //Lista de usuarios
                listUserOrder.addAll(listPlayer);
            }
            else{
                listUserOrder.addAll(repeatingDiccUser.get(iniciative));   //Los usuarios ordenados una vez
            }
        }
        return listUserOrder;
    }

    
    @Transactional
    public List<Usuario> generateOrderByPlayer(List<Usuario> listUser){
        List<Usuario> list= new ArrayList<>();        // Lista de usuarios a ordenar
        List<Integer> listInteger= new ArrayList<>();        //Lista de iniciativas
        Set<Integer> setIniciative= new HashSet<>();        //conjunto de la primera iniciativa de cada jugador
        for(Usuario u: listUser){
            setIniciative.add(u.getInicialListCardsByPlayer().get(u.getInicialListCardsByPlayer().size()-1)); //Conjunto con las iniciativas de cada jugador
        }
        if(setIniciative.size()<listUser.size()){  //Si la primera iniciativa de los jugadores es menor que los jugadores, significa que hay jugadores que tiene la misma iniciativa
            List<Usuario> orderList=generateOrderListByUser(listUser);  //Metodo para ordenar los jugadores que quedan
            list.addAll(orderList);
        }
        else{  //Esto se da en el caso en que los jugadores sean distintos 
            for(Integer i: setIniciative){   //Lista de integer de los jugadores
                listInteger.add(i);
            }
            Collections.sort(listInteger);  //ordenados de menor a mayor
            for(Integer orderIniciative: listInteger){   //recorro las iniciativas
                for(Usuario player: listUser){     // recorro los usuarios
                    if(player.getInicialListCardsByPlayer().get(player.getInicialListCardsByPlayer().size()-1)==orderIniciative){  //si son iguales las inicaitivas entra dentro del bucle
                        list.add(player);
                    }
                }
            }
        }
        return list;
    }

    @Transactional
    public void generateTurnsByPlayers(Round round, List<Usuario> numPlayers){
        List<Turn> turns = new ArrayList<>();
        if(round.getNumber()>1){
            List<Usuario> listUser= generateOrderByPlayer(numPlayers);
            round.setPlayers(listUser);
            save(round);
        }
        for(int i = 0; i < numPlayers.size(); i++){
            if(!numPlayers.get(i).getGameEnded()){
                Turn turn = new Turn();
                turn.setRound(round);
                turn.setUsuario(numPlayers.get(i));
                turnService.save(turn);
                turns.add(turn);
                numPlayers.get(i).setTurn(turn);
                usuarioService.save(numPlayers.get(i));
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
                turn.setUsuario(gameService.NotEndedPlayers(round.getPlayers()).get(e));
                turnService.save(turn);
                turns.add(turn);
                gameService.NotEndedPlayers(round.getPlayers()).get(e).setTurn(turn);
                usuarioService.save(gameService.NotEndedPlayers(round.getPlayers()).get(e));
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
        List<Usuario> players = gameService.NotEndedPlayers(game.getPlayers());
        
        if(players.size() == 1 && gameService.checkLostPuzzle(game) != null){
            if(gameService.checkLostPuzzle(game)){
                players.get(0).setGameEnded(true);
                usuarioService.save(player);
                gameService.endGame(game);
            }
            else if(!gameService.checkLostPuzzle(game)){
                gameService.endGame(game);
            }
        }

        if(players.size() == 2){
            if(gameService.checkDrawVS(game)){
                Usuario p1 = gameService.checkLostVS(game).get(0);
                p1.setGameEnded(true);
                Usuario p2 = gameService.checkLostVS(game).get(1);
                p2.setGameEnded(true);
                usuarioService.save(p1);
                usuarioService.save(p2);
                gameService.endGame(game);
            }else{
                if(gameService.checkLostVS(game).size() > 0){
                    Usuario p = gameService.checkLostVS(game).get(0);
                    p.setGameEnded(true); 
                    usuarioService.save(p);
                    players.remove(p);
                    gameService.endGame(game);
                }
            }
        }else if(players.size() > 2){
            for(Usuario user: gameService.checkLostVS(game)){
                user.setGameEnded(true);
                turns.remove(turnService.getByUsername(user.getUsername()));
                usuarioService.save(user);
                players.remove(user);
            }
            
        }
        Integer count = turnService.getByUsername(player.getUsername()).getCardCounter();
        count += 1;
        Turn t = player.getTurn();
        t.setCardCounter(count);
        turnService.save(t);
        if(game.getRound().getNumber() >= 2){
            if(player.getEnergy().getPowers().get(powerService.findById(4)).booleanValue()){
                handService.generateDefaultHand(deckService.getDeckFromPlayer(player));
                energyService.allFalse(player);
                count-=1;
                t.setCardCounter(count);
                turnService.save(t);
                return;

            }else if(player.getEnergy().getPowers().get(powerService.findById(2)).booleanValue()){
                energyService.allFalse(player);
                 
            }else if(player.getEnergy().getPowers().get(powerService.findById(1)).booleanValue()){
                
                if (player.getTurn().getCardCounter()==3){
                    energyService.allFalse(player);
                }else if(player.getTurn().getCardCounter()<3){
                    return;
                }
            }else if(gameService.getGameByPlayer(player).getGameMode()==GameMode.VERSUS && player.getTurn().getCardCounter() < 2 && turns.contains(player.getTurn())){
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
            for(Usuario p : players){
            handService.generateDefaultHand(deckService.getDeckFromPlayer(p));
            }
            Round round = game.getRound();
            round.setNumber(game.getRound().getNumber()+1);
            round.setGame(game);
            round.setPlayers(players);
            save(round);
            generateTurnsByPlayers(round, players);
            save(round);
            game.setRound(round);
        }
        save(game.getRound());
    }
}
