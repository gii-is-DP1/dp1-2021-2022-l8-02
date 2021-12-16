package org.springframework.samples.endofline.puzzle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuzzleTileService {

    @Autowired
    private PuzzleTileRepository puzzleTileRepository;

    public List<PuzzleTile> findAllByPuzzleId(Integer puzzleId) {
        return puzzleTileRepository.findAllByPuzzleId(puzzleId);
    }
    
}
