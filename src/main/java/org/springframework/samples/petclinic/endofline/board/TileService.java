package org.springframework.samples.petclinic.endofline.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TileService {

    @Autowired
    private TileRepository tileRepository;

    public void save(Tile tile) {
        tileRepository.save(tile);
    }
    
}
