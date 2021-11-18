package org.springframework.samples.endofline.board;

import org.springframework.beans.factory.annotation.Autowired;
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
    
}
