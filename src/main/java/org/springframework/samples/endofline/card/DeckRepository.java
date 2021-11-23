package org.springframework.samples.endofline.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Integer> {

    @Query("SELECT deck FROM Deck deck WHERE deck.user.username=?1")
    Deck findDeckByPlayerUsername(String username);
    
}
