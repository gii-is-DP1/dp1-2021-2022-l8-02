package org.springframework.samples.petclinic.usuario;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    Collection<Usuario> findAll();
    
}
