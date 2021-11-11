package org.springframework.samples.petclinic.carta;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartaService {
    @Autowired
    private CartaRepository cartaRepo;

    @Transactional
    public int cartaCount(){
        return (int) cartaRepo.count();
    }

    public Collection<Carta> findAll(){
        return cartaRepo.findAll();
    }



}
