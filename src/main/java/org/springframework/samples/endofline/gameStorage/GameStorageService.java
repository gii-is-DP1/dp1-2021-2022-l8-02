package org.springframework.samples.endofline.gameStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameStorageService {
    

    @Autowired
    GameStorageRepository gameStorageRepo;
    

    /*@Transactional
    public void save(GameStorage games){
        GameStorageRepository.save(games);
    }*/

    @Transactional
    public void save(GameStorage g){
        gameStorageRepo.save(g);
    }

    public GameStorage getStorageByName(String name){
        return gameStorageRepo.findByName(name);
    }
}
