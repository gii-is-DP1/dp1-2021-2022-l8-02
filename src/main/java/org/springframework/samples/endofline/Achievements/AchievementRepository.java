package org.springframework.samples.endofline.Achievements;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.endofline.usuario.Usuario;

public interface AchievementRepository extends CrudRepository<Achievement, Integer> {
    
    Collection<Achievement> findAll();

    @Query("SELECT * FORM Archievemts WHERE Archievements.user.=?1")
    public Collection<Achievement> findAchievementsByUser(Usuario user);
    
    
}
