package org.springframework.samples.endofline.power;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface powerRepository extends CrudRepository<Power, Integer> {
    
    List<Power> findAll();

    @Query("SELECT power FROM Power power WHERE power.name=?1")
    Power findByName(String name);
    
}
