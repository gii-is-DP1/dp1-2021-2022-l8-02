package org.springframework.samples.petclinic.usuario;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepo;

    public Collection<Usuario> findAll(){
        return usuarioRepo.findAll();
    }
    
}
