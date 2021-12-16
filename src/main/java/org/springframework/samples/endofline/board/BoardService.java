package org.springframework.samples.endofline.board;

import java.util.List;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.board.exceptions.NotUrTurnException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;

import org.springframework.samples.endofline.card.CardType;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.card.Hand;
import org.springframework.samples.endofline.card.HandService;
import org.springframework.samples.endofline.usuario.Usuario;

import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.RoundService;
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
    private HandService handService;

    @Autowired
    private StatisticsGamesService statisticsGamesService;

    @Autowired
    private GameService gameService;

    @Autowired
    private RoundService roundService;

    @Transactional
    public void playCard(Usuario player, Card card, Tile tile) throws InvalidMoveException, NotUrTurnException{
        Game game = gameService.getGameByPlayer(player);
        if (game.getRound().getTurns().get(0).getUsuario().equals(player)) {
            Deck deck = deckService.getDeckFromPlayer(player);
            Hand hand = handService.findHandByDeck(deck);
            if (hand != null && hand.getCards().contains(card)) {
                // TODO: Logica de validacion de una jugada aqui?
                hand.getCards().remove(card);
                handService.save(hand);
                tile.setCard(card);
                tileService.save(tile);
            } else {
                throw new InvalidMoveException();
            }

            StatisticsGames statisticsGames = statisticsGamesService.findStatisticsGamesByUserGames(player, game);
            Map<Card, Integer> mapSet = statisticsGamesService.userMap(card, statisticsGames.getMap());
            statisticsGames.setMap(mapSet);
            Integer pointNew = statisticsGames.getPoint() + card.getCardType().getIniciative();
            statisticsGames.setPoint(pointNew);
            // Guardar los datos una vez actualizados
            statisticsGamesService.save(statisticsGames);
            roundService.refreshRound(game, player);
            gameService.save(game);
        }else{
            throw new NotUrTurnException();
        }
    }

    public Deck deckFromPlayers(Usuario player) {
        Deck deck = deckService.getDeckFromPlayer(player);
        return deck;
    }

    public Hand handByDeck(Deck deck) {
        Hand hand = handService.findHandByDeck(deck);
        return hand;
    }

    public List<CardType> getAllCardTypes() {
        return deckService.AllCardTypes();
    }

    public Tile tileByCoords(Board board, Integer x, Integer y) {
        Tile tile = tileService.findTileByCoordsAndBoard(board, x, y);
        return tile;
    }

    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void generateVersusBoard(Board board) {

        int numPlayers = board.getGame().getPlayers().size();
        int size = 0;

        if (numPlayers < 4) {
            size = 7;
        } else if (numPlayers > 3) {
            size = 9;
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                tileService.save(tile);
            }
        }

    }

    @Transactional
    public void generatePuzzleBoard(Board board) {

        int size = 7;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                tileService.save(tile);
            }
        }

    }

    @Transactional
    public void generateSolitaireBoard(Board board) {

        int size = 5;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                tile.setBoard(board);
                if (x == 2 && y == 3) {
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
