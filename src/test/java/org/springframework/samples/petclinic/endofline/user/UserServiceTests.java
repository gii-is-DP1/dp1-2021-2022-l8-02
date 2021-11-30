package org.springframework.samples.petclinic.endofline.user;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTests {

    @Autowired
    private UsuarioService userService;

    @Test
    void shouldFindAll(){
        Collection<Usuario> users = this.userService.findAll();
        assertThat(users.size()!=0);
    }

    @Test
    void shouldInsertUser(){
        Collection<Usuario> users=this.userService.findAll();
        int found = users.size();

        Usuario user = new Usuario();
        user.setEmail("kvothelaud@gmail.com");
        user.setUsername("KvotheRuh15");
        user.setPassword("kvothe123");
        this.userService.save(user);
        
        users=this.userService.findAll();
        assertThat(users.size()==(found + 1));

    }
    @Test
    void shouldNoInsertUser(){
        Usuario user = new Usuario();
        user.setEmail("kvothelaud@gmail.com");
        user.setUsername("KvotheRuh15");
        user.setPassword("kvothe123");
        this.userService.save(user);

        Usuario user2 = new Usuario();
        user2.setUsername("KvotheRuh15");
        user2.setPassword("kvothe123");
        assertThrows(Exception.class, ()-> {this.userService.save(user2);});
        
        Usuario user1 = new Usuario();
        user1.setEmail("kvothelaud@gmail.com");
        user1.setUsername("KvotheRuh15");
        user1.setPassword("kvothe123");
        assertThrows(Exception.class, ()-> {this.userService.save(user1);});

         /*NO LANZAN EL ERROR QUE DEBERIAN*/

    }
    
    @Test
    void shouldFindUserByUsername() {
        Optional<Usuario> user = userService.findByUsername("Juangr4");
        assertNotNull(user.get());
    }
}
