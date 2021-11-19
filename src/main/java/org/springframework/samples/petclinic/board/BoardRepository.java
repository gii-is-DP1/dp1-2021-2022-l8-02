package org.springframework.samples.petclinic.board;

import java.util.Collection;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Integer> {

    public Collection<Board> findAllBoards();

    public Optional<Board> boardById(Integer id);
}
