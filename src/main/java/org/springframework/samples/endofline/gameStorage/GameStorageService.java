package org.springframework.samples.endofline.gameStorage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameStorageService {
    

    @Autowired
    GameStorageRepository gameStorageRepo;
    

    @Transactional
    public List<GameStorage> findAll(){
        return gameStorageRepo.findAll();
    }
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

    public List<GameStorage> myGames(Usuario user){
        List<GameStorage> res = new ArrayList<>();
        List<GameStorage> all = gameStorageRepo.findAll();
        for(GameStorage gs: all){
            if(gs.getPlayers().get(0)==user.getUsername()){
                res.add(gs);
            }
        }
        return res;
        
    }
}
