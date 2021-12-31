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
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.game.exceptions.GameNotFoundException;
import org.springframework.samples.endofline.usuario.Usuario;

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

    @Autowired
    public GameService(GameRepository gameRepository, BoardService boardService, DeckService deckService, TileService tileService, CardService cardService, RoundService roundService, HandService handService) {

        this.gameRepository = gameRepository;
        this.boardService = boardService;
        this.deckService = deckService;
        this.tileService = tileService;
        this.cardService = cardService;
        this.handService = handService;
        this.roundService = roundService;
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

    public List<Integer> getFirstRoundInitiatives(Map<Usuario, List<Integer>> m){
        List<Integer> res = new ArrayList<>();
        for(List<Integer> ls:m.values()){
            res.add(ls.get(0));
        }Collections.sort(res);
        return res;
    }

    @Transactional
    public void startGame(Game game) {

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
        Map<Usuario, List<Integer>> dicc= new HashMap<>();
        Random random= new Random();
        for(Usuario user: game.getPlayers()){
            List<Integer> list= new ArrayList<>();
            Deck deck= deckService.getDeckFromPlayer(user);
            Integer sumCards= deck.getCards().size();
            Integer valRandom= random.nextInt(sumCards);
            Card card= deck.getCards().get(valRandom);
            Integer iniciative= card.getCardType().getIniciative();
            list.add(iniciative);
            dicc.put(user,list);
        }

        List<Integer> firstRoundInitiatives = getFirstRoundInitiatives(dicc);
        List<Usuario> usersList = new ArrayList<>();
        for(Integer e: firstRoundInitiatives){
            List<Usuario> auxLs = new ArrayList<>();
            for(Usuario u: dicc.keySet()){
                if(dicc.get(u).get(0)==e){
                    auxLs.add(u);
                }
            }
        Collections.shuffle(auxLs);
        usersList.addAll(auxLs);
        }
        round.setPlayers(usersList);
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
            }
        }
        game.setRound(round);
        boardService.save(board);
        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);
    }
    
}
