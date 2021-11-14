package org.springframework.samples.petclinic.endofline.board;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {

    public static Board emptyBoard(Integer size) {
        Board board = new Board();

        List<Tile> tiles = new ArrayList<>();
        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                Tile tile = new Tile();
                
                tile.setBoard(board);
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                
                tiles.add(tile);
            }
        }

        board.setSize(size);
        board.setTiles(tiles);

        return board;
    }
    
}
