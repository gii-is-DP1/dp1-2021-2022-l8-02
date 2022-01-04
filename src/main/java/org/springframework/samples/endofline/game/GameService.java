package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.TileService;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.card.HandService;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.game.exceptions.GameNotFoundException;
import org.springframework.samples.endofline.game.exceptions.TwoPlayersAtLeastException;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private GameRepository gameRepository;
    private BoardService boardService;
    private DeckService deckService;
    private TileService tileService;
    private CardService cardService;
    private HandService handService;
    private RoundService roundService;
    private EnergyService energyService;
    private PowerService powerService;
    private UsuarioService usuarioService;

    @Autowired
    public GameService(PowerService powerService, EnergyService energyService, GameRepository gameRepository, BoardService boardService, DeckService deckService, TileService tileService, CardService cardService, RoundService roundService, HandService handService, UsuarioService usuarioService) {

        this.gameRepository = gameRepository;
        this.boardService = boardService;
        this.deckService = deckService;
        this.tileService = tileService;
        this.cardService = cardService;
        this.handService = handService;
        this.roundService = roundService;
        this.energyService = energyService;
        this.powerService = powerService;
        this.usuarioService = usuarioService;
    }

    public Collection<Game> getGames() {
        return gameRepository.findAll();
    }

    @Transactional
    public void save(Game game){
        gameRepository.save(game);
    }

    public Game findGame(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()) {
            return game.get();
        }
        throw new GameNotFoundException();
    }

    @Transactional
    public void createGame(Game game) throws DuplicatedGameNameException {
        game.setGameState(GameState.LOBBY);

        Game otherGame = gameRepository.findByName(game.getName());
        if(otherGame != null)   throw new DuplicatedGameNameException();

        gameRepository.save(game);
    }

    public Game getGameByPlayer(Usuario player) {
        return gameRepository.getGameByPlayerUsername(player.getUsername());
    }

    @Transactional
    public void joinGame(Game game, Usuario player) {
        leaveGame(player);
        game.getPlayers().add(player);
        gameRepository.save(game);
    }

    @Transactional
    public void leaveGame(Usuario player) {
        Game currentGame = gameRepository.getGameByPlayerUsername(player.getUsername());
        if(currentGame != null) {
            currentGame.getPlayers().remove(player);
            if(currentGame.getPlayers().size() == 0) {
                gameRepository.delete(currentGame);
            } else {
                gameRepository.save(currentGame);
            }
        }
    }

    // public Map<Usuario,List<Integer>> diccConteinsAll(Map<Usuario,List<Integer>> dicc){

    // }
    // public Map<Usuario,List<Integer>> turnsForPlayer(List<Usuario>players){
    //     Map<Usuario,List<Integer>> dicc= new HashMap<>();

    // }

    // public List<Integer> getFirstRoundInitiatives(Map<Usuario, List<Integer> m){
    //     List<Integer> res = new ArrayList<>();
    //     for(List<Integer> ls:m.values()){
    //         if(ls.size() == 0){
    //             res.add(ls.get(0));
    //         }
    //         else{
    //             res.add(ls.get(ls.size()-1));
    //         }
    //     }Collections.sort(res);
    //     return res;
    // }

    @Transactional
    public void startGame(Game game) throws TwoPlayersAtLeastException {
        if(game.getPlayers().size()==1){
            throw new TwoPlayersAtLeastException();
        }
        Board board = new Board();
        board.setGame(game);
        boardService.save(board);

        switch(game.getGameMode()) {
            case PUZZLE:
                boardService.generatePuzzleBoard(board);
                break;
            case SOLITAIRE:
                boardService.generateSolitaireBoard(board);
                break;
            default:
            /*INICIALIZAR ENERGIA A CADA JUGADOR*/
                energyService.initEnergy(game.getPlayers(), powerService.findAll());
                boardService.generateVersusBoard(board);
        }

        for(Integer i = 0; i < game.getPlayers().size(); i++){
            Deck deck = deckService.generateDefaultDeck(game.getPlayers().get(i), CardColor.values()[i]);
            handService.generateDefaultHand(deck);
        }

        Round round = new Round();
        round.setGame(game);

        //Metodo para decidir a inicio que usuarios, juegan.(Crear un diccionario donde se guarde jugador y iniciativa, si la iniciativa 
        //es igual entonces )
        

        
        for(Usuario u: game.getPlayers()){
            u.setInicialListCardsByPlayer(new ArrayList<>());
            usuarioService.save(u);
        }

        List<Usuario> usersList = new ArrayList<>();
        List<Integer> firstRoundInitiatives = roundService.generateInicialOrderByPlayer(game.getPlayers());
            for(Integer e: firstRoundInitiatives){
                List<Usuario> auxLs = new ArrayList<>();
                for(Usuario u: game.getPlayers()){
                    //Sacariamos del dicc la lista correspondiente a cada usuario, le hariamos un size y lo sustituiriamos por 0.
                    if(u.getInicialListCardsByPlayer().get(0)==e){
                        auxLs.add(u);
                    }
                }
            Collections.shuffle(auxLs);
            usersList.addAll(auxLs);
        }
        round.setPlayers(new ArrayList<>());
        round.getPlayers().addAll(usersList);
        roundService.save(round);
        

        //Carta prueba partidas Versus con 1 persona 
        Card sPrueba = new Card();
        sPrueba.setCardType(cardService.findCardTypeByIniciative(-1));
        sPrueba.setColor(CardColor.GREEN);
        cardService.save(sPrueba);

        if(game.getGameMode() == GameMode.VERSUS){
            
            int numplayers = game.getPlayers().size();
            List<Card> cardList = new ArrayList<>(cardService.autoColorAssignInitCards(numplayers));
            if(numplayers < 2){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardForLess3Players(board, cardList.get(0), sPrueba);
            }else if(numplayers == 2){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardForLess3Players(board, cardList.get(0), cardList.get(1));
            }else if(numplayers == 3){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor3Players(board, cardList.get(0), cardList.get(1), cardList.get(2));
            }else if(numplayers == 4){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor4Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3));
            }else if(numplayers == 5){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor5Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4));

            }else if(numplayers == 6){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor6Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4), cardList.get(5));
            }else if(numplayers == 7){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor7Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4), cardList.get(5), cardList.get(6));
            }else if(numplayers == 8){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor8Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4), cardList.get(5), cardList.get(6), cardList.get(7));
            }

            
            
            
            
        }
        game.setRound(round);
        boardService.save(board);
        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);


    }

    public List<Game> getGameByState(GameState state) {
        return gameRepository.getGameByGameState(state);
    }
}
