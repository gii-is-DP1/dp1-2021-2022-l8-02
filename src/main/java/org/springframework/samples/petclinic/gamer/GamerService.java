package org.springframework.samples.petclinic.gamer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamerService {

    @Autowired
    private GamerRepository gameRepository;

    public Gamer findGamerId(Integer id){
        return gameRepository.findById(id).get();
    }

}
