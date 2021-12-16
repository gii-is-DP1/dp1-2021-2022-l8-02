package org.springframework.samples.endofline.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HandRepository extends CrudRepository<Hand,Integer>{

    @Query("SELECT hand FROM Hand hand WHERE hand.deck=?1")
    public Hand findHandByDeck(Deck deck);
}
