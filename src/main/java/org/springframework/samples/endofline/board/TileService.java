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
        tile1.setCard(sRed);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 4, 6);
        tile2.setCard(sGreen);
        save(tile2);
    }

    public void setFirstCardFor3Players(Board board, Card sRed, Card sGreen, Card sWhite){
        Tile tile1 = findTileByCoordsAndBoard(board, 2, 6);
        tile1.setCard(sRed);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 4, 6);
        tile2.setCard(sGreen);
        save(tile2);
        Tile tile3 = findTileByCoordsAndBoard(board, 3, 5);
        tile3.setCard(sWhite);
        save(tile3);
    }

    public void setFirstCardFor4Players(Board board, Card sRed, Card sGreen, Card sWhite, Card sBlue){
        Tile tile1 = findTileByCoordsAndBoard(board, 2, 6);
        tile1.setCard(sRed);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 4, 6);
        tile2.setCard(sGreen);
        save(tile2);
        Tile tile3 = findTileByCoordsAndBoard(board, 3, 5);
        tile3.setCard(sWhite);
        save(tile3);
        Tile tile4 = findTileByCoordsAndBoard(board, 5, 3);
        tile4.setCard(sWhite);
        save(tile4);
    }

    public void setFirstCardFor5Players(Board board, Card sRed, Card sGreen, Card sWhite, Card sBlue, Card sPurple){
        Tile tile1 = findTileByCoordsAndBoard(board, 2, 6);
        tile1.setCard(sRed);
        save(tile1);
        Tile tile2 = findTileByCoordsAndBoard(board, 4, 6);
        tile2.setCard(sGreen);
        save(tile2);
        Tile tile3 = findTileByCoordsAndBoard(board, 3, 5);
        tile3.setCard(sWhite);
        save(tile3);
        Tile tile4 = findTileByCoordsAndBoard(board, 5, 3);
        tile4.setCard(sWhite);
        save(tile4);
        Tile tile5 = findTileByCoordsAndBoard(board, 6, 4);
        tile5.setCard(sPurple);
        save(tile5);
    }

}
