package org.springframework.samples.endofline.achievements;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;


public interface AchievementRepository extends CrudRepository<Achievement, Integer> {
    
    public Collection<Achievement> findAll();

   
    
    
}
