package org.springframework.samples.endofline.power;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface powerRepository extends CrudRepository<Power, Integer> {
    
    List<Power> findAll();
    
}
