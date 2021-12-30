package org.springframework.samples.petclinic.endofline.GameStatisticsTest;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.board.StatisticsGamesService;
import org.springframework.samples.endofline.board.StatisticsGames;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.game.exceptions.TwoPlayersAtLeastException;
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
    protected StatisticsGamesService gStatsService;

    @BeforeEach
    void newGame() {

        Game game = new Game();
        game.setName("Juego Prueba Estadisticas");
        game.setGameMode(GameMode.SOLITAIRE);
        game.setHidden(false);
        Game game2 = new Game();
        game2.setName("Juego Prueba2");
        game2.setGameMode(GameMode.PUZZLE);
        game2.setHidden(false);
        Usuario user = new Usuario();
        Usuario user2 = new Usuario();
        Authorities aut = new Authorities();
        Set<Authorities> setAut = new HashSet<>();

        //Usuario1
        user.setUsername("player");
        user.setPassword("pass");
        user.setEmail("player@endofline.com");
        aut.setAuthority("jugador");
        setAut.add(aut);
        user.setAuthorities(setAut);
        userService.save(user);

        //Usuario2
        user2.setUsername("player2");
        user2.setPassword("pass");
        user2.setEmail("player2@endofline.com");
        user2.setAuthorities(setAut);
        userService.save(user2);

        //Game1
        try{

            gameService.createGame(game);
            gameService.joinGame(game, user);
            gameService.startGame(game);

        }catch(DuplicatedGameNameException e){

            System.out.println("Ya existe la partida");

        } catch (TwoPlayersAtLeastException e) {
            e.printStackTrace();
        }

        //Game2
        try{

            gameService.createGame(game2);
            gameService.joinGame(game2, user2);
            gameService.startGame(game2);

        }catch(DuplicatedGameNameException e){

            System.out.println("Ya existe la partida");

        } catch (TwoPlayersAtLeastException e) {
            e.printStackTrace();
        }

        StatisticsGames stat = new StatisticsGames();
        stat.setUser(user2);
        stat.setGame(game2);
        stat.setPoint(2);
        gStatsService.save(stat);

    }

    @Test
    void shouldSaveStat(){

        Game game = gameService.getGameByPlayer(userService.findByUsername("player2").get());
        StatisticsGames stat = gStatsService.findStatisticsGamesByUserGames(userService.findByUsername("player2").get(), game);
        assertEquals(2, stat.getPoint());

    }

    @Test
    void shouldCreateStatWithGame(){

        Game game1 = gameService.getGameByPlayer(userService.findByUsername("player").get());
        StatisticsGames stats = gStatsService.findStatisticsGamesByUserGames(userService.findByUsername("player").get(), game1);
        assertNull(stats);

    }

}