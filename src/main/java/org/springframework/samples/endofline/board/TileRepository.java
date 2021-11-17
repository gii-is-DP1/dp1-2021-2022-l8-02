package org.springframework.samples.endofline.board;

import org.springframework.data.repository.CrudRepository;

public interface TileRepository extends CrudRepository<Tile, Integer> {
    
}
