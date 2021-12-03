package org.springframework.samples.endofline.energies;

import org.springframework.data.repository.CrudRepository;

public interface energyRepository extends CrudRepository<energy, Integer> {
    
}
