package org.springframework.samples.endofline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.TileService;
import org.springframework.samples.endofline.board.TileState;
import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.card.CardType;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.game.exceptions.GameIsFullException;
import org.springframework.samples.endofline.game.exceptions.GameNotFoundException;
import org.springframework.samples.endofline.usuario.Authorities;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.EnabledIf;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {
    @Autowired
    protected GameService gameService;
    @Autowired
    protected UsuarioService userService;
    @Autowired
    protected CardService cardService;
    @Autowired
    protected TileService tileService;
    @Autowired
    protected BoardService boardService;

    @BeforeEach
    @Test
    void newGame() {

        Game game = new Game();
        game.setName("Juego Prueba Estadisticas");
        game.setGameMode(GameMode.SOLITAIRE);
        game.setHidden(false);
        Usuario user = new Usuario();
        Authorities aut = new Authorities();
        Set<Authorities> setAut = new HashSet<>();
        Card card= new Card();
        Board board= new Board();
        Tile tile1= new Tile();
        Tile tile2= new Tile();
        Tile tile3= new Tile();
        Tile tile4= new Tile();
        List<Tile> listTiles= new ArrayList<>();

        //Usuario
        user.setUsername("player");
        user.setPassword("pass");
        user.setEmail("player@endofline.com");
        aut.setAuthority("jugador");
        setAut.add(aut);
        user.setAuthorities(setAut);
        userService.save(user);

        //Card_Type
        CardType cardType=cardService.findAllCardTypes().get(3);

        //Card
        card.setCardType(cardType);
        card.setColor(CardColor.RED);
        cardService.save(card);

        //Tile
        boardService.save(board);
        tile1.setCard(card);
        tile1.setX(0);
        tile1.setY(0);
        tile1.setTileState(TileState.TAKEN);
        tile1.setBoard(board);
        tileService.save(tile1);
        
        
        tile2.setX(1);
        tile2.setY(0);
        tile2.setTileState(TileState.FREE);
        tile2.setBoard(board);
        tileService.save(tile2);

        tile3.setX(0);
        tile3.setY(1);
        tile3.setTileState(TileState.FREE);
        tile3.setBoard(board);
        tileService.save(tile3);

        tile4.setX(1);
        tile4.setY(1);
        tile4.setTileState(TileState.FREE);
        tile4.setBoard(board);
        tileService.save(tile4);

        listTiles.add(tile1);
        listTiles.add(tile2);
        listTiles.add(tile3);
        listTiles.add(tile4);

        //Board
        
        board.setTiles(listTiles);
        

        try{

            gameService.createGame(game);
            board.setGame(game);
            boardService.save(board);
            gameService.joinGame(game, user);

        }catch(DuplicatedGameNameException e){

            System.out.println("Ya existe la partida");

        }catch (GameIsFullException e){
            System.out.println("La partida está llena");
        }
    }

    @Test
    void findGame(){
        Usuario user=userService.findByUsername("player").get();
        Game game= gameService.getGameByPlayer(user);
        assertNotNull(game);
    }

    @Test
    void findGameWithOutPlayer(){
        Usuario user=userService.findByUsername("player").get();
        Game game= gameService.getGameByPlayer(user);
        Usuario user2= game.getPlayers().get(0);
        gameService.leaveGame(user2);
        assertThrows(GameNotFoundException.class, () -> gameService.findGame(1));
    }

    @Test
    void JoinInSolitarieMode(){
        Game game = gameService.getGameByPlayer(userService.findByUsername("player").get());
        Usuario user = new Usuario();
        try{
            gameService.joinGame(game, user);
        }catch (GameIsFullException e){
            System.out.println("La partida está llena");
        }
        assertNotEquals(game.getPlayers().size(), 1);
    }

    @Test
    void JoinInVersusMode(){
        Game game = gameService.getGameByPlayer(userService.findByUsername("player").get());
        game.setGameMode(GameMode.VERSUS);
        Usuario user = new Usuario();
        try{
            gameService.joinGame(game, user);
        }catch (GameIsFullException e){
            System.out.println("La partida está llena");
        }
        assertNotEquals(game.getPlayers().size(), 1);
    }

    @Test 
    void GameWithTitle() {
        Game game2 = new Game();
        game2.setName("Juego Prueba Estadisticas");
        game2.setGameMode(GameMode.SOLITAIRE);
        game2.setHidden(false);
        assertThrows(DuplicatedGameNameException.class, () -> gameService.createGame(game2));
    }

    @Test
    void GameWithMoreThan8Players() {
        Game gameAux = new Game();
        gameAux.setHidden(false);
        gameAux.setGameMode(GameMode.VERSUS);

            Usuario user1 = new Usuario();
        user1.setUsername("userTest1");
        user1.setPassword("u");
        user1.setEmail("userTest1@u.com");
            Usuario user2 = new Usuario();
        user2.setPassword("u");
        user2.setEmail("userTest2@u.com");
        user2.setUsername("userTest2");
            Usuario user3 = new Usuario();
        user3.setPassword("u");
        user3.setEmail("userTest3@u.com");
        user3.setUsername("userTest3");
            Usuario user4 = new Usuario();
        user4.setPassword("u");
        user4.setEmail("userTest4@u.com");
        user4.setUsername("userTest4");
            Usuario user5 = new Usuario();
        user5.setPassword("u");
        user5.setEmail("userTest5@u.com");
        user5.setUsername("userTest5");
            Usuario user6 = new Usuario();
        user6.setPassword("u");
        user6.setEmail("userTest6@u.com");
        user6.setUsername("userTest6");
            Usuario user7 = new Usuario();
        user7.setPassword("u");
        user7.setEmail("userTest7@u.com");
        user7.setUsername("userTest7");
            Usuario user8 = new Usuario();
        user8.setPassword("u");
        user8.setEmail("userTest8@u.com");
        user8.setUsername("userTest8");
        Usuario user9 = new Usuario();
        user9.setPassword("u");
        user9.setEmail("userTest9@u.com");
        user9.setUsername("userTest9");
        List<Usuario> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        gameAux.setPlayers(users);
        assertThrows(GameIsFullException.class, () -> gameService.joinGame(gameAux, user9));
    }
}
