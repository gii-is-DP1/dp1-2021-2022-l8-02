package org.springframework.samples.petclinic.usuario;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepo;

    public Collection<Usuario> findAll(){
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> findById(int id){
        return usuarioRepo.findById(id);
    }

    public void delete(Usuario usuario) {
		usuarioRepo.deleteById(usuario.getId());

	}

	public void save(@Valid Usuario usuario) {
		usuarioRepo.save(usuario);
	}
    
    public Optional<Usuario> findByUsername(String username) {
		return usuarioRepo.findByUsername(username);
	}
}
