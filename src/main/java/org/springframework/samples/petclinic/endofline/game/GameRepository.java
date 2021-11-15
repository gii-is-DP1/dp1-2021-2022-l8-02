package org.springframework.samples.petclinic.endofline.game;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

    Collection<Game> findAll();
    
}
