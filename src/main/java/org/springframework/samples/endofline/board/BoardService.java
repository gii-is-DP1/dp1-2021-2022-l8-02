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
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.RoundService;
import org.springframework.samples.endofline.power.PowerService;
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
    private GameService gameService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private PuzzleTileService puzzleTileService;

    @Autowired
    private PathService pathService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private EnergyService energyService;


    @Autowired
    private UsuarioService usuarioService;
    

    @Transactional
    // TODO: No podemos tener un metodo tan largo, hay que hacer minimetodos y luego
    // llamarlos, como en gameStart
    public void playCard(Usuario player, Card card, Tile tile) throws InvalidMoveException, NotUrTurnException, TimeOutException {
        Game game = gameService.getGameByPlayer(player);
        Path p = game.getBoard().getPaths().get(card.getColor().ordinal());
        List<Tile> occupiedTiles = p.getOccupiedTiles();
        Tile lastTile = null;
        if(player.getEnergy().getPowers().get(powerService.findById(3)).booleanValue()){
            lastTile = occupiedTiles.get(occupiedTiles.size() - 2);
        }else{
            lastTile = occupiedTiles.get(occupiedTiles.size() - 1);
        }
        List<Tile> availableTiles = getAdjacents(lastTile, player, p);
        if (!game.getGameMode().equals(GameMode.VERSUS) || game.getRound().getTurns().get(0).getUsuario().equals(player)) {
            System.out.println(game.getRound().getTurns().get(0).getStartTime());
            if (compareHour(game.getRound().getTurns().get(0).getStartTime())) {
                
                Deck deck = deckService.getDeckFromPlayer(player);
                Hand hand = handService.findHandByDeck(deck);
                if (hand != null && (hand.getCards().contains(card) || hand.getDismissCardsList().contains(card))&& availableTiles.contains(tile)) {
                    // TODO: Logica de validacion de una jugada aqui?
                    card.setRotation(cardService.calculateRotation(tile, lastTile));
                    cardService.save(card);
                    player.getInicialListCardsByPlayer().add(card.getCardType().getIniciative());
                    usuarioService.save(player);
                    hand.getCards().remove(card);
                    hand.getDismissCardsList().remove(card);
                    handService.save(hand);
                    tile.setCard(card);
                    tile.setTileState(TileState.TAKEN);
                    tileService.save(tile);
                    p.getOccupiedTiles().add(tile);
                    pathService.save(p);
                } else {
                    throw new InvalidMoveException();

                }
                gameService.save(game);
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

    // Segundos en un d??a: 86399 (23:59:59)
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
            if (substract < 86100) {
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

        if (numPlayers > 7) {
            size = 13;
        } else if (numPlayers > 5) {
            size = 11;
        } else if (numPlayers > 3) {
            size = 9;
        } else {
            size = 7;
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
    public void generatePuzzleBoard(Board board) { //I THINK THIS CAN BE USED FOR SOLITAIRE AS WELL


        
        int size = 5;

        Random random = new Random();

        int maxImplementedPuzzles = 60;

        List<PuzzleTile> tiles = puzzleTileService.findAllByPuzzleId(random.nextInt(maxImplementedPuzzles - 1) + 1);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileState(TileState.FREE);
                if (x == 2 && y == 2) {
                    Card card = new Card();
                    card.setColor(CardColor.RED);
                    card.setCardType(cardService.findCardTypeByIniciative(-1));
                    card.setRotation(Direction.NORTH);
                    System.out.println(card.getCardType().getIniciative());
                    cardService.save(card);
                    tile.setCard(card);
                    tile.setTileState(TileState.TAKEN);
                    pathService.initPath(board,card.getColor(), tile);
                }else{
                    for(PuzzleTile pt: tiles) {
                        if(pt.getX() == x && pt.getY() == y) {
                            Card card = new Card();
                            card.setColor(CardColor.RED);
                            card.setCardType(pt.getCardType());
                            cardService.save(card);
                            tile.setTileState(TileState.TAKEN);
                            tile.setCard(card);
                            break;
                        }
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
                if (x == 2 && y == 2) {
                    Card card = new Card();
                    card.setColor(CardColor.RED);
                    card.setCardType(cardService.findCardTypeByIniciative(-1));
                    card.setRotation(Direction.NORTH);
                    cardService.save(card);
                    tile.setCard(card);
                    tile.setTileState(TileState.TAKEN);
                    pathService.initPath(board,card.getColor(), tile);
                }
                tile.setBoard(board);
                tileService.save(tile);
            }
        }
    }

    public List<Tile> getAdjacents(Tile tile, Usuario user, Path p){
        System.out.println(p.getOccupiedTiles().size());
        if(user.getEnergy().getPowers().get(powerService.findById(3)).booleanValue() == true ){
            Tile tile2 = p.getOccupiedTiles().get(p.getOccupiedTiles().size()-2);
            Card card = p.getOccupiedTiles().get(p.getOccupiedTiles().size()-2).getCard();
            energyService.allFalse(user);
            return card.getCardType().getDirections()
            .stream().map(Enum::ordinal)
            .map(x -> (x + tile2.getCard().getRotation().ordinal())%Direction.values().length)
            .map(x -> Direction.values()[x])
            .map(x -> tileService.creaTile(x, tile2, tile2.getBoard()))
            .collect(Collectors.toList());
        }
        return tile.getCard().getCardType().getDirections()
                    .stream().map(Enum::ordinal)
                    .map(x -> (x + tile.getCard().getRotation().ordinal())%Direction.values().length)
                    .map(x -> Direction.values()[x])
                    .map(x -> tileService.creaTile(x, tile, tile.getBoard()))
                    .filter(x->!x.getTileState().equals(TileState.TAKEN))
                    .collect(Collectors.toList());
    }

    public void delete(Board board) {
        boardRepository.delete(board);
    }
}
