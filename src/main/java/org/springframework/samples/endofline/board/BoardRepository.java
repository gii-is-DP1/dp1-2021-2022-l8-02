package org.springframework.samples.endofline.board;

import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    
}
