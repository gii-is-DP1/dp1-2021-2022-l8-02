package org.springframework.samples.endofline.card;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query("SELECT cardType FROM CardType cardType ORDER BY cardType.id")
    List<CardType> findAllCardTypes();

    @Query("SELECT cardType FROM CardType cardType WHERE cardType.iniciative=?1")
    CardType findCardTypeByIniciative(Integer iniciative);
    
}
