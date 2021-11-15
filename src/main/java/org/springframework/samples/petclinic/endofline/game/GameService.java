package org.springframework.samples.petclinic.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.endofline.board.Board;
import org.springframework.samples.petclinic.endofline.board.BoardService;
import org.springframework.samples.petclinic.endofline.board.Tile;
import org.springframework.samples.petclinic.endofline.board.TileService;
import org.springframework.samples.petclinic.endofline.board.TileState;
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

    public void startGame(Integer gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalArgumentException::new);

        Board board = new Board();
        board.setGame(game);
        boardService.save(board);

        List<Tile> tiles = new ArrayList<>();

        switch(game.getGameMode()) {
            case VERSUS:
                for(int x=0; x<5; x++) {
                    for(int y=0; y<5; y++) {
                        Tile tile = new Tile();
                        tile.setX(x);
                        tile.setY(y);
                        tile.setTileState(TileState.FREE);
                        tile.setBoard(board);
                        tiles.add(tile);
                        tileService.save(tile);
                    }
                }
                break;
            case PUZZLE:
            for(int x=0; x<7; x++) {
                for(int y=0; y<7; y++) {
                    Tile tile = new Tile();
                    tile.setX(x);
                    tile.setY(y);
                    tile.setTileState(TileState.FREE);
                    tile.setBoard(board);
                    tiles.add(tile);
                    tileService.save(tile);
                }
            }
                break;
            default:
                throw new IllegalArgumentException(); // InvalidGameModeException
        }

        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);
    }
    
}
