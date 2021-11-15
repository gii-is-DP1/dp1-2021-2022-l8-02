package org.springframework.samples.petclinic.endofline.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TileService tileService;

    public void save(Board board) {
        // board.getTiles().stream().forEach(tileService::save);
        boardRepository.save(board);
    }
    
}
