package org.springframework.samples.petclinic.endOnline.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer>{

    public Collection<Game> findAll();

    public Optional<Game> findGameById(Integer id);

    public Optional<Game> findGameByName(String name);

}
