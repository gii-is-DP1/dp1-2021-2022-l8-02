package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

@Service
public class GameService {

    private GameRepository gameRepository;
    private BoardService boardService;
    private DeckService deckService;
    private TileService tileService;
    private CardService cardService;
    private HandService handService;
    private RoundService roundService;
    private TurnService turnService;

    @Autowired
    public GameService(GameRepository gameRepository, BoardService boardService, DeckService deckService, TileService tileService, CardService cardService, RoundService roundService, TurnService turnService, HandService handService) {

        this.gameRepository = gameRepository;
        this.boardService = boardService;
        this.deckService = deckService;
        this.tileService = tileService;
        this.cardService = cardService;
        this.handService = handService;
        this.roundService = roundService;
        this.turnService = turnService;
    }

    public Collection<Game> getGames() {
        return gameRepository.findAll();
    }


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

    public void createGame(Game game) throws DuplicatedGameNameException {
        game.setGameState(GameState.LOBBY);

        Game otherGame = gameRepository.findByName(game.getName());
        if(otherGame != null)   throw new DuplicatedGameNameException();

        gameRepository.save(game);
    }

    public Game getGameByPlayer(Usuario player) {
        return gameRepository.getGameByPlayerUsername(player.getUsername());
    }

    public void joinGame(Game game, Usuario player) {
        leaveGame(player);
        game.getPlayers().add(player);
        gameRepository.save(game);
    }

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

        // Round round = new Round();
        // round.setGame(game);
        // round.setPlayers(new ArrayList<>(game.getPlayers()));

        if(game.getGameMode() == GameMode.VERSUS){
            int numplayers = game.getPlayers().size();
            List<Card> cardList = new ArrayList<>(cardService.autoColorAssignInitCards(numplayers));
            if(numplayers < 3){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardForLess3Players(board, cardList.get(0), cardList.get(1));
            }else if(numplayers == 3){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor3Players(board, cardList.get(0), cardList.get(1), cardList.get(2));
            }else if(numplayers == 4){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor4Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3));
            }else if(numplayers == 5){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor5Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4));
            }
        }
        // game.setRound(round);
        boardService.save(board);
        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);
    }
    
}
