package org.springframework.samples.endofline.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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

    public void delete(Usuario usuario) {
		usuarioRepo.delete(usuario);
  	}

    @Transactional
	  public void save(@Valid Usuario usuario) {
		usuarioRepo.save(usuario);
	  }
    
    public Optional<Usuario> findByUsername(String username) {
		return usuarioRepo.findByUsername(username);
	  }

    public List<String> getallUsernames(){
      List<String> usernames = new ArrayList<>();
      for(Usuario user : findAll()){
        usernames.add(user.getUsername());
      }
      return usernames;
    }

    public List<String> getallEmails(){
      List<String> emails = new ArrayList<>();
      for(Usuario user : findAll()){
        emails.add(user.getEmail());
      }
      return emails;
    }

    public void setStatFalse(List<Usuario> users){
      for(Usuario u : users){
        u.setGameEnded(false);
      }
    }

}
