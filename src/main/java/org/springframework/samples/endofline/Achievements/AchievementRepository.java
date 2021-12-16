package org.springframework.samples.endofline.Achievements;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;


public interface AchievementRepository extends CrudRepository<Achievement, Integer> {
    
    public Collection<Achievement> findAll();  
    
}
