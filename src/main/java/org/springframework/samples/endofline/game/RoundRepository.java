package org.springframework.samples.endofline.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Integer>{

    public Optional<Round> findById(Integer id);

    public Collection<Round> findAll();

    public Round findByGame(Game game);
}
