package org.springframework.samples.endofline.puzzle;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PuzzleTileRepository extends CrudRepository<PuzzleTile, Integer> {

    List<PuzzleTile> findAllByPuzzleId(Integer puzzleId);
    
}
