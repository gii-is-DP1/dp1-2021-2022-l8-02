package org.springframework.samples.endofline.gameStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameStorageService {
    
    @Autowired
    GameStorageService gameStorageService;

    @Autowired
    GameService gameService;

    @Transactional
    public List<Game> getGamesByPlayer(Usuario player){
        List<Game> result = new ArrayList<>();
        Collection<Game> allGames = gameService.getGames();
        for(Game g : allGames){
            if(g.getPlayers().contains(player)){
                result.add(g);
            }
        }
        return result;
    }

    /*@Transactional
    public void save(GameStorage games){
        GameStorageRepository.save(games);
    }*/

}
