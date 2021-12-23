package org.springframework.samples.endofline.board;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.card.Card;
import org.springframework.stereotype.Service;

import org.springframework.samples.endofline.card.Direction;

@Service
public class TileService {

    @Autowired
    private TileRepository tileRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    public PathService pathService;

    public Tile findTileByCoordsAndBoard(Board board, Integer x, Integer y) {
        return tileRepository.findTileByCoordsAndBoard(board, x, y);
    }

    @Transactional
    public void save(Tile tile) {
        tileRepository.save(tile);
    }
    
    @Transactional
    public void setFirstCardForLess3Players(Board board, Card sRed, Card sGreen){
        Tile tile1 = findTileByCoordsAndBoard(board, 2, 6);
        tile1.setBoard(board);
        tile1.setCard(sRed);
        tile1.setTileState(TileState.TAKEN);
        pathService.initPath(board,sRed.getColor(), tile1);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 4, 6);
        tile2.setBoard(board);
        tile2.setCard(sGreen);
        tile2.setTileState(TileState.TAKEN);
        pathService.initPath(board,sGreen.getColor(), tile2);
        save(tile2);
    }

    @Transactional
    public void setFirstCardFor3Players(Board board, Card sRed, Card sGreen, Card sWhite){
        Tile tile1 = findTileByCoordsAndBoard(board, 2, 6);
        tile1.setBoard(board);
        tile1.setCard(sRed);
        tile1.setTileState(TileState.TAKEN);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 4, 6);
        tile2.setBoard(board);
        tile2.setCard(sGreen);
        tile2.setTileState(TileState.TAKEN);
        save(tile2);
        Tile tile3 = findTileByCoordsAndBoard(board, 3, 5);
        tile3.setBoard(board);
        tile3.setCard(sWhite);
        tile3.setTileState(TileState.TAKEN);
        save(tile3);
    }

    @Transactional
    public void setFirstCardFor4Players(Board board, Card sRed, Card sGreen, Card sWhite, Card sBlue){
        Tile tile1 = findTileByCoordsAndBoard(board, 3, 3);
        tile1.setBoard(board);
        tile1.setCard(sRed);
        tile1.setTileState(TileState.TAKEN);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 5, 5);
        tile2.setBoard(board);
        tile2.setCard(sGreen);
        tile2.setTileState(TileState.TAKEN);
        save(tile2);
        Tile tile3 = findTileByCoordsAndBoard(board, 3, 5);
        tile3.setBoard(board);
        tile3.setCard(sWhite);
        tile3.setTileState(TileState.TAKEN);
        save(tile3);
        Tile tile4 = findTileByCoordsAndBoard(board, 5, 3);
        tile4.setBoard(board);
        tile4.setCard(sBlue);
        tile4.setTileState(TileState.TAKEN);
        save(tile4);
    }

    @Transactional
    public void setFirstCardFor5Players(Board board, Card sRed, Card sGreen, Card sWhite, Card sBlue, Card sPurple){
        Tile tile1 = findTileByCoordsAndBoard(board, 3, 3);
        tile1.setBoard(board);
        tile1.setCard(sRed);
        tile1.setTileState(TileState.TAKEN);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 5, 5);
        tile2.setBoard(board);
        tile2.setCard(sGreen);
        tile2.setTileState(TileState.TAKEN);
        save(tile2);
        Tile tile3 = findTileByCoordsAndBoard(board, 3, 5);
        tile3.setBoard(board);
        tile3.setCard(sWhite);
        tile3.setTileState(TileState.TAKEN);
        save(tile3);
        Tile tile4 = findTileByCoordsAndBoard(board, 5, 3);
        tile4.setBoard(board);
        tile4.setCard(sBlue);
        tile4.setTileState(TileState.TAKEN);
        save(tile4);
        Tile tile5 = findTileByCoordsAndBoard(board, 6, 4);
        tile5.setBoard(board);
        tile5.setCard(sPurple);
        tile5.setTileState(TileState.TAKEN);
        save(tile5);
    }

    public boolean samePosition(Tile tile1, Tile tile2){
        return tile1.getX() == tile2.getX() && tile1.getY() == tile2.getY();
    }

    public Tile creaTile(Direction direction, Tile t, Board board){
        Tile tile = new Tile();
        Double aux = Math.sqrt(board.getTiles().size());
        Integer mod = aux.intValue();
        switch(direction.ordinal()){
            case 0:
                tile = findTileByCoordsAndBoard(board, t.getX(), (t.getY()-1 + mod)%mod);
                break;
            case 1:
                tile = findTileByCoordsAndBoard(board, (t.getX()+1 + mod)%mod, t.getY());
                break;
            case 2:
                tile = findTileByCoordsAndBoard(board, t.getX(), (t.getY()+1 + mod)%mod);
                break;
            case 3:
                tile = findTileByCoordsAndBoard(board, (t.getX()-1 + mod)%mod, t.getY());
                break;
        }tile.setTileState(TileState.AVAILABLE);
        return tile;
    }

}
