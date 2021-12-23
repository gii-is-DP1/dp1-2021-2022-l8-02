package org.springframework.samples.endofline.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PathService {
    
    @Autowired
    public PathRepository pathRepository;

    @Autowired
    public BoardRepository boardRepository;

    @Autowired
    public TileRepository tileRepository;

    
    @Transactional
    public void save(Path path) {
        pathRepository.save(path);
    }

    public void initPath(Board board, CardColor color, Tile tile){
            Path p = new Path();
            List<Tile> ls = new ArrayList<>();
            ls.add(tile);
            p.setBoard(board);
            p.setColor(color);
            p.setOccupiedTiles(ls);
            save(p);
    }
}
