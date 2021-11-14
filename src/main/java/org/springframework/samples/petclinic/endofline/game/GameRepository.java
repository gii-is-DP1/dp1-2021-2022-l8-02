package org.springframework.samples.petclinic.endofline.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
    
}
