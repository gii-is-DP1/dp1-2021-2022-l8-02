package org.springframework.samples.endofline.Achievements;

import java.util.Collection;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.usuario.Usuario;

public class AchievementService {

    @Autowired
    AchievementRepository achievementRepo;

    @Transactional
    public Collection<Achievement> findAchievementsByUser(Usuario user){
        return achievementRepo.findAchievementsByUser(user);
    }
    @Transactional
    public Collection<Achievement> findAll(){
        return achievementRepo.findAll();
    }

    public void save (Achievement a){
        achievementRepo.save(a);
    }
    
}
