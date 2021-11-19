package org.springframework.samples.petclinic.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    
    @Transactional
    public Card cardById(int id){
        return cardRepository.findById(id).get();
    }
    
}
