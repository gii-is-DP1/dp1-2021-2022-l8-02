package org.springframework.samples.endofline.board;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TileService tileService;

    @Autowired
    private CardService cardService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private StatisticsGamesService statisticsGamesService;

    @Autowired
    private GameService gameService; 

    public void playCard(Usuario player, Card card, Tile tile) throws InvalidMoveException {
        Deck deck = deckService.getDeckFromPlayer(player);
        if(deck != null && deck.getCards().contains(card)) {
            // TODO: Logica de validacion de una jugada aqui?
            deck.getCards().remove(card);
            deckService.save(deck);
            tile.setCard(card);
            tileService.save(tile);
        } else {
            throw new InvalidMoveException();
        }
        
        Game game=gameService.getGameByPlayer(player);
        StatisticsGames statisticsGames=statisticsGamesService.findStatisticsGamesByUserGames(player, game);
        Map<Card, Integer> mapSet= statisticsGamesService.userMap(card, statisticsGames.getMap());
        statisticsGames.setMap(mapSet);
        Integer pointNew= statisticsGames.point+card.getCardType().getIniciative();
        statisticsGames.setPoint(pointNew);
        //Guardar los datos una vez actualizados
        statisticsGamesService.save(statisticsGames);
    }
    
 
    public void save(Board board) {
        boardRepository.save(board);
    }

    public void generateVersusBoard(Board board, int players) {

        int size = 5;

        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                tileService.save(tile);
            }
        }

    }

    public void generatePuzzleBoard(Board board) {

        int size = 7;

        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                tileService.save(tile);
            }
        }

    }

    public void generateSolitaireBoard(Board board) {

        int size = 5;

        for(int x=0; x<size; x++) {
            for(int y=0; y<size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                if(x == 2 && y == 3) {
                    // Creacion de cardType
                    Card card = new Card();
                    card.setColor(CardColor.RED);
                    card.setCardType(cardService.findCardTypeByIniciative(-1));
                    System.out.println(card.getCardType().getIniciative());
                    cardService.save(card);
                    tile.setCard(card);
                }
                tileService.save(tile);
            }
        }
    }
    
}
