package org.springframework.samples.petclinic.endofline.GameStatisticsTest;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.TileState;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardType;
import org.springframework.samples.endofline.card.Direction;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.GameState;
import org.springframework.samples.endofline.usuario.Authorities;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameStatisticsTest {

    @Autowired
    protected GameService gameService;

    @BeforeEach
    void newGame() {
        Usuario usuario = new Usuario();
        Game gameG = new Game();
        Board board = new Board();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Tile tile4 = new Tile();
        Card card = new Card();
        CardType cardType = new CardType();
        List<Tile> listTile = new ArrayList<>();
        List<Usuario> listUserName = new ArrayList<>();
        Authorities authorities = new Authorities();
        Set<Authorities> setAut = new HashSet<>();
        List<Direction> direction = new ArrayList<>();

        authorities.setId(1);
        authorities.setUsuario(usuario);
        authorities.setAuthority("Admin");

        usuario.setEmail("newuser@gmail.com");
        usuario.setAuthorities(setAut);

        usuario.setPassword("123ttf");
        usuario.setUsername("player");

        direction.add(Direction.NORTH);
        direction.add(Direction.WEST);
        cardType.setId(1);
        cardType.setDirections(direction);
        cardType.setDirections(direction);
        cardType.setIniciative(5);
        cardType.setName("photoCard.jpg");

        card.setId(1);
        card.setCardType(cardType);
        card.setColor(CardColor.RED);

        tile1.setId(1);
        tile1.setCard(card);
        tile1.setTileState(TileState.TAKEN);
        tile1.setX(0);
        tile1.setY(0);
        tile1.setBoard(board);

        tile2.setId(2);
        tile2.setTileState(TileState.FREE);
        tile2.setX(1);
        tile2.setY(0);
        tile2.setBoard(board);

        tile3.setId(3);
        tile3.setTileState(TileState.FREE);
        tile3.setX(0);
        tile3.setY(1);
        tile3.setBoard(board);

        tile4.setId(4);
        tile4.setTileState(TileState.FREE);
        tile4.setX(1);
        tile4.setY(1);
        tile4.setBoard(board);

        listTile.add(tile1);
        listTile.add(tile2);
        listTile.add(tile3);
        listTile.add(tile4);

        board.setId(1);
        board.setTiles(listTile);
        board.setGame(gameG);
        gameG.setName("Game1");
        gameG.setId(1);
        gameG.setHidden(false);
        listUserName.add(usuario);
        gameG.setPlayers(listUserName);
        gameG.setGameMode(GameMode.SOLITAIRE);
        gameG.setGameState(GameState.PLAYING);
        gameG.setBoard(board);

        try {
            gameService.createGame(gameG);
        } catch (Exception e) {
            System.out.println("Ya existe este juego");
        }

    }

}