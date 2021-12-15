package org.springframework.samples.endofline.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<CardType> findAllCardTypes() {
        return cardRepository.findAllCardTypes();
    }

    public CardType findCardTypeByIniciative(Integer iniciative) {
        return cardRepository.findCardTypeByIniciative(iniciative);
    }

    public CardType findCardTypeByName(String name) {
        return cardRepository.findCardTypeByName(name);
    }

    public void save(Card card) {
        cardRepository.save(card);
    }

    public List<Card> autoColorAssignInitCards(int numPlayers){
        List<Card> list = new ArrayList<>();
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            save(card);
            list.add(card);
        }
        return list;
    }
    
}
