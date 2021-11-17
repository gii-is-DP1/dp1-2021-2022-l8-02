package org.springframework.samples.petclinic.gamer;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GamerRepository extends CrudRepository<Gamer, Integer>{

//   public Collection<Gamer> findAllGamer();
   
//   public Optional<Gamer> findGamerId(Integer id);

   public Optional<Gamer> findGamerByName(String name);

}
