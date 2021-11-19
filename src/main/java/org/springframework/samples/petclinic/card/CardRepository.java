package org.springframework.samples.petclinic.card;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query("SELECT cardType FROM CardType cardType ORDER BY cardType.id")
    Collection<CardType> findAllCardTypes();
    
    
}
