package org.springframework.samples.endofline.gameStorage;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GameStorageRepository extends CrudRepository<GameStorage, Integer>{
    
    GameStorage findByName(String name);

    List<GameStorage> findAll();

}
