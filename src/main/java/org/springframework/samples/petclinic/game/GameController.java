package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GameController extends CrudRepository<Game, Integer>{

    public Collection<Game> findAll();

    public Optional<Game> findGameById(Integer id);

    public Optional<Game> findGameByName(String name);
}
