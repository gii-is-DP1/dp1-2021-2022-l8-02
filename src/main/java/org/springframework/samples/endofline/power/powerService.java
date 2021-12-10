package org.springframework.samples.endofline.power;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.energies.energyRepository;
import org.springframework.samples.endofline.game.GameRepository;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioRepository;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class powerService {
    
    @Autowired
    powerRepository powerRepo;

    energyRepository eneryRepo;

    GameService gameService;

    UsuarioService userService;

    UsuarioRepository usuarioRepo;

    @Transactional
    public Power findById(Integer id){
        return powerRepo.findById(id).get();
    }

    @Transactional
    public Collection<Power> findAll(){
        return powerRepo.findAll();
    }


    private Usuario getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return userService.findByUsername(user.getUsername()).orElseThrow(IllegalArgumentException::new);
    }


    /*public void playerUsePower(Integer id, String username){
        Usuario user = gameService.;
         
    }*/
}
