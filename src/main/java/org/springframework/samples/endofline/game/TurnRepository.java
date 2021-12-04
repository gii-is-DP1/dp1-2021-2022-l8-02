package org.springframework.samples.endofline.game;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TurnRepository extends CrudRepository<Turn, Integer>{

    Collection<Turn> findAll();

    @Query("SELECT turn FROM Turn turn WHERE turn.usuario.username = ?1")
    Turn getTurnByUsername(String username);
}
