package org.springframework.samples.endofline.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.TileService;
import org.springframework.samples.endofline.board.TileState;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameRepository gameRepository;
    private BoardService boardService;
    private TileService tileService;

    @Autowired
    public GameService(GameRepository gameRepository, BoardService boardService, TileService tileService) {
        this.gameRepository = gameRepository;
        this.boardService = boardService;
        this.tileService = tileService;
    }

    public Collection<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game findGame(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()) {
            return game.get();
        }
        throw new IllegalArgumentException();
    }

    public void createGame(Game game) {
        game.setGameState(GameState.LOBBY);

        gameRepository.save(game);
    }

    public void joinGame(Game game, Usuario player) {
        Game currentGame = gameRepository.getGameByPlayerUsername(player.getUsername());
        if(currentGame != null) {
            currentGame.getPlayers().remove(player);
            gameRepository.save(currentGame);
        }
        game.getPlayers().add(player);
        gameRepository.save(game);
    }

    public void startGame(Integer gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalArgumentException::new);

        Board board = new Board();
        board.setGame(game);
        boardService.save(board);

        switch(game.getGameMode()) {
            case VERSUS:
                generateVersusBoard(board, 2);
                break;
            case PUZZLE:
                generatePuzzleBoard(board);
                break;
            default:
                throw new IllegalArgumentException(); // InvalidGameModeException
        }

        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);
    }

    // Auxiliar Methods
    private void generateVersusBoard(Board board, int players) {

        int size = 5;

        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                tileService.save(tile);
            }
        }

    }

    private void generatePuzzleBoard(Board board) {

        int size = 7;

        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                tileService.save(tile);
            }
        }

    }
    
}
