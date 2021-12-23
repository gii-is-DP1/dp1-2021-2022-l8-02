package org.springframework.samples.endofline.power;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class powerService {
    
    @Autowired
    powerRepository powerRepo;

   

    @Transactional
    public Power findById(Integer id){
        return powerRepo.findById(id).get();
    }

    @Transactional
    public List<Power> findAll(){
        return powerRepo.findAll();
    }


    



}
