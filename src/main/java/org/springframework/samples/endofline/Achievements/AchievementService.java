package org.springframework.samples.endofline.Achievements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.samples.endofline.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.usuario.Usuario;



public class AchievementService {

    @Autowired
    AchievementRepository achievementRepo;

    GameService gameService;

   /* @Transactional
    public Collection<Achievement> findAchievementsByUser(Usuario user){
        return achievementRepo.findAchievementsByUser(user);
    }*/
    @Transactional
    public Collection<Achievement> findAll(){
        return achievementRepo.findAll();
    }
   /* public Achievement findAchievement(Integer id){
        Optional<Achievement> achievement = achievementRepo.findById(id);
        if(achievement.isPresent()) {
            return achievement.get();
        }
        throw new RuntimeException();
       
    }*/

    public void save (Achievement a){
        achievementRepo.save(a);
    }

/*
    public List<Achievement> playGames(Usuario user){
        List<Game> games = new ArrayList<Game>();
        games.add(gameService.getGameByPlayer(user));
        List<Achievement> achievements= new ArrayList<Achievement>();
        if(games.size()==5){
            achievements.add(findAchievement(1));
        }else if (games.size()==10){
            achievements.add(findAchievement(2));
        }else if(games.size()==50){
            achievements.add(findAchievement(3));
        }
        return achievements;

    }*/



    
}
