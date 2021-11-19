package org.springframework.samples.petclinic.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Board finBoardById(int id){
        return boardRepository.findById(id).get();
    }
}
