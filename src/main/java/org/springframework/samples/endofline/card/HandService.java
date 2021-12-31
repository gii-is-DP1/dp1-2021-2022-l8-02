package org.springframework.samples.endofline.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.endofline.card.exceptions.PlayCardWhitHandSizeLessThanFive;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.Round;
import org.springframework.samples.endofline.game.Turn;
import org.springframework.samples.endofline.game.TurnService;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.stereotype.Service;

@Service
public class HandService {

    @Autowired
    HandRepository handRepository;

    @Autowired
    DeckService deckService;

    @Autowired
    TurnService turnService;
    
    @Autowired
    PowerService powerService;

    @Autowired
    GameService gameService;
    
    public Hand findHandByDeck(Deck deck){
        return handRepository.findHandByDeck(deck);
    }

    @Transactional
    public void save(Hand hand){
        handRepository.save(hand);
    }


    public void generateChangeHand(Deck deck) throws PlayCardWhitHandSizeLessThanFive{
        Hand hand= findHandByDeck(deck);
        // String userName=deck.getUser().toString();
        // Turn turn= turnService.getByUsername(userName);
        // Round round= turn.getRound();
        if(hand.getCards().size()<5){
            throw new PlayCardWhitHandSizeLessThanFive();
        }
        else{
        List<Card> listHand= new ArrayList<>();
        listHand.addAll(hand.getCards());
        Random random= new Random();
        for(Card card:listHand){
            deck.getCards().add(card);
            hand.getCards().remove(card);
            save(hand);  
        }
        while(hand.getCards().size()<5){
            Integer rand= random.nextInt(deck.getCards().size());
            Card card=deck.getCards().get(rand);
            hand.getCards().add(card);
            deck.getCards().remove(card);
            deckService.save(deck); 
        }
        save(hand);
        }
    }

    public Hand generateDefaultHand(Deck deck){
        Hand hand = findHandByDeck(deck);
        Random random = new Random();
        if(hand == null){
            hand = new Hand();
            hand.setDeck(deck);
            hand.setCards(new ArrayList<>());
        }
        if(gameService.getGameByPlayer(deck.getUser()).getGameMode() ==  GameMode.VERSUS && 
        deck.getUser().getEnergy().getPowers().get(powerService.findById(4)).booleanValue() ==  true){
            while(hand.getCards().size()<6){
                Integer rand= random.nextInt(deck.getCards().size());
                Card card=deck.getCards().get(rand);
                hand.getCards().add(card);
                deck.getCards().remove(card);
                deckService.save(deck); 
            }
        
        }else{
        while(hand.getCards().size()<5){
                Integer rand = random.nextInt(deck.getCards().size());
                Card card=deck.getCards().get(rand);
                hand.getCards().add(card);
                deck.getCards().remove(card);
                deckService.save(deck); 
            }
        }    
        save(hand);
        return hand;
    }
    
}
