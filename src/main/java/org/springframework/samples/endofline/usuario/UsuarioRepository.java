package org.springframework.samples.endofline.usuario;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    public Collection<Usuario> findAll();

    public Optional<Usuario> findByUsername(String username);

    Page<Usuario> findAll(Pageable pageable);
    
}
