package org.springframework.samples.petclinic.endofline.board;

import org.springframework.data.repository.CrudRepository;

public interface TileRepository extends CrudRepository<Tile, Integer> {
    
}
