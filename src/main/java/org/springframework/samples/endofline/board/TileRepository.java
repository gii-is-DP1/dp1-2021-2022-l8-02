package org.springframework.samples.endofline.board;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TileRepository extends CrudRepository<Tile, Integer> {

    @Query("SELECT tile FROM Tile tile WHERE tile.board=?1 AND tile.x=?2 AND tile.y=?3")
    Tile findTileByCoordsAndBoard(Board board, Integer x, Integer y);
    
}
