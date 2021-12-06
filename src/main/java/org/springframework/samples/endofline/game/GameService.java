package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.TileService;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.card.DeckService;
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
    private RoundService roundService;
    private TurnService turnService;

    @Autowired
    public GameService(GameRepository gameRepository, BoardService boardService, DeckService deckService, TileService tileService, CardService cardService, RoundService roundService, TurnService turnService) {
        this.gameRepository = gameRepository;
        this.boardService = boardService;
        this.deckService = deckService;
        this.tileService = tileService;
        this.cardService = cardService;
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

        for(Usuario player: game.getPlayers()) {
            deckService.generateDefaultDeck(player, CardColor.RED);
        }

        // Round round = new Round();
        // round.setGame(game);
        // round.setPlayers(new ArrayList<>(game.getPlayers()));

        if(game.getGameMode() == GameMode.VERSUS){
            int numplayers = game.getPlayers().size();
            Card sRed = new Card();
            Card sGreen = new Card();
            Card sWhite = new Card();
            Card sBlue = new Card();
            Card sPurple = new Card();
            sRed.setCardType(cardService.findCardTypeByIniciative(-1));
            sRed.setColor(CardColor.RED);
            cardService.save(sRed);
            sGreen.setCardType(cardService.findCardTypeByIniciative(-1));
            sGreen.setColor(CardColor.GREEN);
            cardService.save(sGreen);
            sWhite.setCardType(cardService.findCardTypeByIniciative(-1));
            sWhite.setColor(CardColor.WHITE);
            cardService.save(sWhite);
            sBlue.setCardType(cardService.findCardTypeByIniciative(-1));
            sBlue.setColor(CardColor.BLUE);
            cardService.save(sBlue);
            sPurple.setCardType(cardService.findCardTypeByIniciative(-1));
            sPurple.setColor(CardColor.PURPLE);
            cardService.save(sPurple);
            if(numplayers < 3){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardForLess3Players(board, sRed, sGreen);
            }else if(numplayers == 3){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor3Players(board, sRed, sGreen, sWhite);
            }else if(numplayers == 4){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor4Players(board, sRed, sGreen, sWhite, sBlue);
            }else if(numplayers == 5){
                // roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor5Players(board, sRed, sGreen, sWhite, sBlue, sPurple);
            }
        }
        // game.setRound(round);
        boardService.save(board);
        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);
    }
    
}
