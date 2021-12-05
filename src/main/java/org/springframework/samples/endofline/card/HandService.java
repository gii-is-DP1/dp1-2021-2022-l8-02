package org.springframework.samples.endofline.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandService {

    @Autowired
    HandRepository handRepository;

    @Autowired
    DeckService deckService;
    
    public Hand findHandByDeck(Deck deck){
        return handRepository.findHandByDeck(deck);
    }

    public void save(Hand hand){
        handRepository.save(hand);
    }

    public Hand generateDefaultHand(Deck deck){
        Hand hand= findHandByDeck(deck);
        Random random= new Random();
        if(hand == null){
            hand= new Hand();
            hand.setDeck(deck);
            hand.setCards(new ArrayList<>());
        }
        
        while(hand.getCards().size()<5){
                Integer rand= random.nextInt(deck.getCards().size());
                Card card=deck.getCards().get(rand);
                hand.getCards().add(card);
                deck.getCards().remove(card);
                deckService.save(deck);
            }
            
        save(hand);
        return hand;
    }
    
}
