package org.springframework.samples.distructor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.game.exceptions.GameNotFoundException;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameCreationTest {

    @Autowired
    GameService gameService;

    @Autowired
    UsuarioService userService;

    @Test
    @Transactional
    void invalidName() {
        Game game = new Game();
        game.setHidden(false);
        game.setGameMode(GameMode.VERSUS);
        assertThrows(Exception.class, () -> gameService.createGame(game));
    }

    @Test
    void duplicatedName() {
        Game game = new Game();
        game.setName("Nombre repetido");
        game.setHidden(true);
        game.setGameMode(GameMode.VERSUS);
        assertDoesNotThrow(() -> gameService.createGame(game));
        
        Game game2 = new Game();
        game2.setName("Nombre repetido");
        game2.setHidden(false);
        game2.setGameMode(GameMode.PUZZLE);
        assertThrows(DuplicatedGameNameException.class, () -> gameService.createGame(game2));
    }

    @Test
    @Disabled
    @Transactional
    void invalidGameMode() {
        Game game = new Game();
        game.setName("Prueba de juego 2");
        game.setHidden(false);
        assertDoesNotThrow(() -> gameService.createGame(game));
    }

    @Test
    @Transactional
    void sucessCreation() {
        Game game = new Game();
        game.setName("Prueba de juego");
        game.setHidden(false);
        game.setGameMode(GameMode.VERSUS);
        assertDoesNotThrow(() -> gameService.createGame(game));
    }

    @Nested
    @DisplayName("New versus game tests")
    @DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
    class WhenNewVersus {

        int versusGameId;

        @BeforeEach
        @Transactional
        void initVersus() {
            Game versusGame = new Game();
            versusGame.setName("Prueba de versus");
            versusGame.setGameMode(GameMode.VERSUS);
            versusGame.setHidden(false);
            
            assertDoesNotThrow(() -> gameService.createGame(versusGame));

            versusGameId = versusGame.getId();

            Usuario hostPlayer = userService.findByUsername("Juangr4").get();

            assertDoesNotThrow(() -> gameService.joinGame(versusGame, hostPlayer));

            Usuario anotherPlayer = userService.findByUsername("chemaccs").get();

            assertDoesNotThrow(() -> gameService.joinGame(versusGame, anotherPlayer));
        }

        @Test
        @Transactional
        void joinVersus() {
            Game versusGame = gameService.findGame(versusGameId);
            Usuario player = userService.findByUsername("kikovilapavon").get();
            assertDoesNotThrow(() -> gameService.joinGame(versusGame, player));
        }

        @Test
        @Transactional
        void leaveVersus() {
            Usuario player = userService.findByUsername("chemaccs").get();
            assertDoesNotThrow(() -> gameService.leaveGame(player));
            assertNull(gameService.getGameByPlayer(player));
            
            Game versusGame = gameService.findGame(versusGameId);
            assertFalse(versusGame.getPlayers().contains(player));

            Usuario hostPlayer = userService.findByUsername("Juangr4").get();
            assertDoesNotThrow(() -> gameService.leaveGame(hostPlayer));
            assertNull(gameService.getGameByPlayer(hostPlayer));

            assertThrows(GameNotFoundException.class, () -> gameService.findGame(versusGameId));
        }

    }

    @Nested
    @DisplayName("New puzzle game tests")
    @DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
    class WhenNewPuzzle {

        int puzzleGameId;
        
        @BeforeEach
        @Transactional
        void initPuzzle() {
            Game puzzleGame = new Game();
            puzzleGame.setName("Prueba de versus");
            puzzleGame.setGameMode(GameMode.VERSUS);
            puzzleGame.setHidden(false);
            
            assertDoesNotThrow(() -> gameService.createGame(puzzleGame));

            puzzleGameId = puzzleGame.getId();

            Usuario hostPlayer = userService.findByUsername("Juangr4").get();

            assertDoesNotThrow(() -> gameService.joinGame(puzzleGame, hostPlayer));
        }

        @Test
        @Disabled
        @Transactional
        void joinPuzzle() {
            Game puzzleGame = gameService.findGame(puzzleGameId);
            Usuario player = userService.findByUsername("chemaccs").get();
            //TODO: Si nadie puede unirse tiene sentido que se muestre en la lista un juego de puzzle?
            assertThrows(Exception.class, () -> gameService.joinGame(puzzleGame, player));
        }

        @Test
        @Transactional
        void leavePuzzle() {
            Usuario hostPlayer = userService.findByUsername("Juangr4").get();
            assertDoesNotThrow(() -> gameService.leaveGame(hostPlayer));
            assertNull(gameService.getGameByPlayer(hostPlayer));

            assertThrows(GameNotFoundException.class, () -> gameService.findGame(puzzleGameId));
        }

    }
    
}
