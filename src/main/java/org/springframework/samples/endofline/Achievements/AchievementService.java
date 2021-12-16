package org.springframework.samples.endofline.Achievements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.samples.endofline.statistics.Statistics;
import org.springframework.samples.endofline.statistics.StatisticsService;

public class AchievementService {

    @Autowired
    AchievementRepository achievementRepo;
    StatisticsService statsService;
    UsuarioService usuarioService;

    public List<Achievement> findAchievementsByUser(String username){
        Usuario user = usuarioService.findByUsername(username).get();
        return user.getAchievements();
    }

    public Collection<Achievement> findAll(){
        return achievementRepo.findAll();
    }
    
    public Achievement findAchievement(Integer id){
        Optional<Achievement> achievement = achievementRepo.findById(id);
        if(achievement.isPresent()) {
            return achievement.get();
        }
        throw new RuntimeException();
       
    }

    @Transactional
    public void save (Achievement a){
        achievementRepo.save(a);
    }

    @Transactional
    public void playGames(String username){
        List<Achievement> achievements= new ArrayList<Achievement>();
        Statistics stats = statsService.findByUser(usuarioService.findByUsername(username).get());
        if(stats.getNumGames()==5){
            achievements.add(findAchievement(1));
        }else if (stats.getNumGames()==10){
            achievements.add(findAchievement(2));
        }else if(stats.getNumGames()==50){
            achievements.add(findAchievement(3));
        }
        usuarioService.findByUsername(username).get().setAchievements(achievements);
    }



    
}
