package org.springframework.samples.petclinic.endofline.StatisticsTest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.statistics.Statistics;
import org.springframework.samples.endofline.statistics.StatisticsService;
import org.springframework.samples.endofline.usuario.Authorities;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatisticsTest {
    
    @Autowired
    protected StatisticsService statsService;
    @Autowired
    protected UsuarioService userService;

    @BeforeEach
    void setUp(){

        Usuario user = new Usuario();
        Authorities aut = new Authorities();
        Set<Authorities> setAut = new HashSet<>();
        
         //Usuario
         user.setUsername("player");
         user.setPassword("pass");
         user.setEmail("player@endofline.com");
         aut.setAuthority("jugador");
         setAut.add(aut);
         user.setAuthorities(setAut);
         userService.save(user);

         Statistics stat = new Statistics();
         stat.setUsuario(user);
         stat.setNumGames(20);
         stat.setDuration(15);
         stat.setNumPlayers(4);
         statsService.save(stat);

    }

    @Test
    void shouldExistStat(){

        Statistics stat = statsService.findByUser(userService.findByUsername("player").get());
        assertNotNull(stat);
    }

}
