package org.springframework.samples.endofline.card;

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

    public void save(Card card) {
        cardRepository.save(card);
    }

    
}
