package org.springframework.samples.petclinic.carta;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CartaRepository extends CrudRepository<Carta, Integer> {
    public Collection<Carta> findAll();


}
