package org.springframework.samples.endofline.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.TileService;
import org.springframework.samples.endofline.board.TileState;
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

    @Autowired
    public GameService(GameRepository gameRepository, BoardService boardService, DeckService deckService) {
        this.gameRepository = gameRepository;
        this.boardService = boardService;
        this.deckService = deckService;
    }

    public Collection<Game> getGames() {
        return gameRepository.findAll();
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
                boardService.generateVersusBoard(board, 2);
        }

        for(Usuario player: game.getPlayers()) {
            deckService.generateDefaultDeck(player, CardColor.RED);
        }

        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);
    }
    
}
