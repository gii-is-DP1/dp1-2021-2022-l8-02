package org.springframework.samples.endofline.Achievements;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.endofline.usuario.Usuario;

public interface AchievementRepository extends CrudRepository<Achievement, Integer> {
    
    public Collection<Achievement> findAll();
/*
   @Query("SELECT * FROM Achievements WHERE Achievements.id=?1")
   public Optional<Achievement> findById(Integer id);*/
    
}
