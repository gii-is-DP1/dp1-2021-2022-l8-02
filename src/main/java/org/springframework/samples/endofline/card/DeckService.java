package org.springframework.samples.endofline.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.stereotype.Service;


@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private HandService handService;


    public List<CardType> AllCardTypes(){
        List<CardType> cardType=cardService.findAllCardTypes();
        return cardType;
    }

    public List<Integer> orderIniciatives(){
        List<Integer> listInteger= new ArrayList<>();
        Set<Integer> orderIniciatives= new HashSet<>(); 
        for(CardType c: AllCardTypes()){  //Todos los tipos de carta
            orderIniciatives.add(c.getIniciative());   //Conjunto con todas las inicitavas
        }
        
        
        for(Integer i: orderIniciatives){    //Iniciativas
            if(i>=0){
            listInteger.add(i);   //Lista de iniciativas
            }
        }
        Collections.sort(listInteger);   //Conjunto ordenado de menor a mayor
        return listInteger;   //lista de inicitaivas ordenadas;
    }

    public Deck getDeckFromPlayer(Usuario player) {
        return deckRepository.findDeckByPlayerUsername(player.getUsername());
    }

    public Map<String, Integer> configuration(){
        Map<String, Integer> dicc= new HashMap<>();
        dicc.put("0", 1);
        dicc.put("1", 9);
        dicc.put("2", 4);
        dicc.put("2b", 4);
        dicc.put("3", 4);
        dicc.put("4", 2);
        dicc.put("5", 1);
        return dicc;
    }

    @Transactional
    public Deck generateDefaultDeck(Usuario player, CardColor color) {
        Deck deck = getDeckFromPlayer(player);
        if (deck != null) {
            Hand hand = handService.findHandByDeck(deck);
            if(hand != null)    handService.delete(hand);
            delete(deck);
        }
        deck = new Deck();
        deck.setUser(player);
        deck.setCards(new ArrayList<>());

        for(Entry<String, Integer> integer : configuration().entrySet()){
            String a=integer.getKey();
            CardType type= cardService.findCardTypeByName(a);
            for (Integer i=0; i<integer.getValue(); i++){
                Card card = new Card();
                card.setColor(color);
                card.setCardType(type);
                cardService.save(card);
                deck.getCards().add(card);
            }
        }
        save(deck);
        return deck;
    }

    @Transactional
    public void save(Deck deck) {
        deckRepository.save(deck);
    }

    @Transactional
    public void delete(Deck deck) {
        deckRepository.delete(deck);
    }
    
}
