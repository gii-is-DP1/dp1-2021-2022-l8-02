package org.springframework.samples.petclinic.endofline.GameStatisticsTest;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.usuario.Authorities;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameStatisticsTest {

    @Autowired
    protected GameService gameService;
    @Autowired
    protected UsuarioService userService;
    @Autowired
    protected CardService cardService;
    @Autowired
    protected 
    

    @BeforeEach
    void newGame() {

        Game game = new Game();
        game.setName("Juego Prueba Estadisticas");
        game.setGameMode(GameMode.SOLITAIRE);
        game.setHidden(false);
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

        try{

            gameService.createGame(game);
            gameService.joinGame(game, user);
            gameService.startGame(game);

        }catch(DuplicatedGameNameException e){

            System.out.println("Ya existe la partida");

        }
    }

    @Test
    void shouldExistGame(){

        Game game1 = gameService.getGameByPlayer(userService.findByUsername("player").get());
        assertNotNull(game1);

    }

    @Test
    void shouldExistStatistics(){

        Game game1 = gameService.getGameByPlayer(userService.findByUsername("player").get());

        assertNotNull(game1);
    }

}