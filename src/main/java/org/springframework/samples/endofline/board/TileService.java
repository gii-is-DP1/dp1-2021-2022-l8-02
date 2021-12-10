package org.springframework.samples.endofline.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.card.Card;
import org.springframework.stereotype.Service;

@Service
public class TileService {

    @Autowired
    private TileRepository tileRepository;

    public Tile findTileByCoordsAndBoard(Board board, Integer x, Integer y) {
        return tileRepository.findTileByCoordsAndBoard(board, x, y);
    }

    public void save(Tile tile) {
        tileRepository.save(tile);
    }
    
    public void setFirstCardForLess3Players(Board board, Card sRed, Card sGreen){
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
    }

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

}
