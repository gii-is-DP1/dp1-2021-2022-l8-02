package org.springframework.samples.endofline.power;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface powerRepository extends CrudRepository<Power, Integer> {
    
    Collection<Power> findAll();
    
}
