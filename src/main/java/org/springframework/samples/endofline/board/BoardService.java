package org.springframework.samples.endofline.board;

import java.time.LocalTime;
import java.util.List;
import javax.transaction.Transactional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.board.exceptions.NotUrTurnException;
import org.springframework.samples.endofline.board.exceptions.TimeOutException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.card.CardType;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.card.Direction;
import org.springframework.samples.endofline.card.Hand;
import org.springframework.samples.endofline.card.HandService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.RoundService;
import org.springframework.samples.endofline.puzzle.PuzzleTile;
import org.springframework.samples.endofline.puzzle.PuzzleTileService;
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

    @Autowired
    private PuzzleTileService puzzleTileService;

    @Autowired
    private PathService pathService;

    @Transactional
    // TODO: No podemos tener un metodo tan largo, hay que hacer minimetodos y luego
    // llamarlos, como en gameStart
    public void playCard(Usuario player, Card card, Tile tile) throws InvalidMoveException, NotUrTurnException, TimeOutException {
        Game game = gameService.getGameByPlayer(player);
        Path p = game.getBoard().getPaths().get(card.getColor().ordinal()); // Returns the path(tiles played)followed by
                                                                            // a player
        List<Tile> occupiedTiles = p.getOccupiedTiles(); // Returns the list with the occupied tiles
        Tile lastTile = occupiedTiles.get(occupiedTiles.size() - 1); // Returns the lastTile of the previous mentioned
                                                                     // list
        List<Tile> availableTiles = getAdjacents(lastTile); // Returns a list of the available tiles respect to the
                                                            // lastTile given
        if (game.getRound().getTurns().get(0).getUsuario().equals(player)) {
            if (compareHour(game.getRound().getTurns().get(0).getStartTime()) == true) { // para el temporizador, aun no
                                                                                         // funciona seguro
                Deck deck = deckService.getDeckFromPlayer(player);
                Hand hand = handService.findHandByDeck(deck);
                if (hand != null && hand.getCards().contains(card) && availableTiles.contains(tile)) {
                    // TODO: Logica de validacion de una jugada aqui?
                    card.setRotation(cardService.calculateRotation(tile, lastTile));
                    cardService.save(card);
                    hand.getCards().remove(card);
                    handService.save(hand);
                    tile.setCard(card);
                    tileService.save(tile);
                    p.getOccupiedTiles().add(tile); // Adds the new tile thats been occupied by the player to his path
                    pathService.save(p);
                    // StatisticsGames statisticsGames = statisticsGamesService.findStatisticsGamesByUserGames(player, game);
                    // Map<Card, Integer> mapSet = statisticsGamesService.userMap(card, statisticsGames.getMap());
                    // statisticsGames.setMap(mapSet);
                    // Integer pointNew = statisticsGames.getPoint() + card.getCardType().getIniciative();
                    // statisticsGames.setPoint(pointNew);
                    // Guardar los datos una vez actualizados
                    // statisticsGamesService.save(statisticsGames);

                } else {
                    throw new InvalidMoveException();
                }
            } else {
                roundService.refreshRound(game, player);
                gameService.save(game);
                throw new TimeOutException();
            }
        } else {
            throw new NotUrTurnException();
        }
        roundService.refreshRound(game, player);
        gameService.save(game);
    }

    // Segundos en un día: 86399 (23:59:59)
    public Boolean compareHour(Integer startTime) {
        Integer hourNow = hourToInteger();
        if (hourNow > startTime) {
            Integer substract = hourNow - startTime;
            if (substract < 300) {
                return true;
            } else {
                return false;
            }
        } else {
            Integer substract = startTime - hourNow;
            if (substract < 86700) {
                return false;
            } else {
                return true;
            }
        }
    }

    public Integer hourToInteger() {
        LocalTime start = java.time.LocalTime.now();
        return start.toSecondOfDay();
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

        int size = 5;

        Random random = new Random();

        int maxImplementedPuzzles = 60;

        List<PuzzleTile> tiles = puzzleTileService.findAllByPuzzleId(random.nextInt(maxImplementedPuzzles - 1) + 1);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + tiles);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                for (PuzzleTile pt : tiles) {
                    if (pt.getX() == x && pt.getY() == y) {
                        Card card = new Card();
                        card.setColor(CardColor.RED);
                        card.setCardType(pt.getCardType());
                        cardService.save(card);
                        tile.setTileState(TileState.TAKEN);
                        tile.setCard(card);
                        break;
                    }
                }
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

    public List<Tile> getAdjacents(Tile tile) {
        return tile.getCard().getCardType().getDirections()
                .stream().map(Enum::ordinal)
                .map(x -> (x + tile.getCard().getRotation().ordinal()) % Direction.values().length)
                .map(x -> Direction.values()[x])
                .map(x -> tileService.creaTile(x, tile, tile.getBoard()))
                .collect(Collectors.toList());
    }
}
