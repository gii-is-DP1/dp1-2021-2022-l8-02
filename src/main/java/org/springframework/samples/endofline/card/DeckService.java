package org.springframework.samples.endofline.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardService cardService;


    public List<CardType> AllCardTypes(){
        List<CardType> cardType=cardService.findAllCardTypes();
        return cardType;
    }

    public Deck getDeckFromPlayer(Usuario player) {
        return deckRepository.findDeckByPlayerUsername(player.getUsername());
    }

  

    public Deck generateDefaultDeck(Usuario player, CardColor color) {
        Deck deck = getDeckFromPlayer(player);
        if(deck == null) {
            deck = new Deck();
            deck.setUser(player);
            deck.setCards(new ArrayList<>());
        }
        for(CardType type: cardService.findAllCardTypes()) {
            Card card = new Card();
            card.setColor(color);
            card.setCardType(type);
            cardService.save(card);
            deck.getCards().add(card);
        }
        save(deck);
        return deck;
    }

    public void save(Deck deck) {
        deckRepository.save(deck);
    }
    
}
