package org.springframework.samples.endofline.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;

import org.springframework.samples.endofline.card.CardType;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.card.Direction;
import org.springframework.samples.endofline.usuario.Usuario;

import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.game.GameService;
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


    public List<Tile> availableTiles(CardColor color, Board board){
        List<Tile> cp  = board.getLines().get(color); //OccupiedTilesList should be used here.
        List<Tile> res = new ArrayList<>();
        String direction = "";
        if(cp.size()==1){
            Tile newTile = new Tile();
            newTile.setX(cp.get(0).getX()); //Are getX() and getY() methods implemented?
            newTile.setY(cp.get(0).getY()-1);
            newTile.setTileState(TileState.AVAILABLE);
            res.add(newTile);
        }else{
            Tile last = cp.get(cp.size()-1);
            Tile penultimate = cp.get(cp.size()-2);
            
            //This first if handles the use of "Marcha Atrás"
            if(last.getX()!=penultimate.getX() && last.getY()!=penultimate.getY()){
                penultimate = cp.get(cp.size()-3);
            }
            if(last.getX()==penultimate.getX()){
                if(last.getY()>penultimate.getY()) direction = "SOUTH";
                if(last.getY()<penultimate.getY()) direction = "NORTH"; //I could have used else instead, but I think this is antoher layer of checking everything is working as expected
                
            }else if(last.getY()==penultimate.getY()){
                if(last.getX()>penultimate.getX()) direction = "EAST";
                if(last.getX()<penultimate.getX()) direction = "WEST";
            }

            res = findAvailableTiles(direction, last, board);
        }return res;
    }

    private List<Tile> findAvailableTiles(String direction, Tile last, Board board){
        List<Tile> res = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        switch(direction){
            case "NORTH":
                ls = last.getCard().getCardType().getDirections().stream().map(x->x.toString()).collect(Collectors.toList());
                break;
            case "EAST":
                for(Direction dir:last.getCard().getCardType().getDirections()){
                    String newDir = "";
                    if(dir.toString().equals("WEST")) newDir = "NORTH"; //This could be easily optimized by directly adding the tile to the list
                    if(dir.toString().equals("NORTH")) newDir = "EAST"; 
                    if(dir.toString().equals("EAST")) newDir = "SOUTH"; 
                    ls.add(newDir);
                }break;
            case "SOUTH":
                for(Direction dir:last.getCard().getCardType().getDirections()){
                    String newDir = "";
                    if(dir.toString().equals("WEST")) newDir = "EAST";
                    if(dir.toString().equals("NORTH")) newDir = "SOUTH";
                    if(dir.toString().equals("EAST")) newDir = "WEST";
                    ls.add(newDir);
                }break;
            case "WEST":
                for(Direction dir:last.getCard().getCardType().getDirections()){
                    String newDir = "";
                    if(dir.toString().equals("EAST")) newDir = "NORTH";
                    if(dir.toString().equals("WEST")) newDir = "SOUTH";
                    if(dir.toString().equals("NORTH")) newDir = "WEST";
                    ls.add(newDir);
                }break;
        }for(String s:ls){ 
            Tile taux = new Tile();
            if(s.equals("NORTH")){
                taux.setX(last.getX());
                taux.setY(last.getY()-1);
                taux.setBoard(board);
                taux.setTileState(TileState.AVAILABLE);
            }else if(s.equals("EAST")){
                taux.setX(last.getX()+1);
                taux.setY(last.getY());
                taux.setBoard(board);
                taux.setTileState(TileState.AVAILABLE);
            }else if(s.equals("WEST")){
                taux.setX(last.getX()-1);
                taux.setY(last.getY());
                taux.setBoard(board);
                taux.setTileState(TileState.AVAILABLE);
            }else if(s.equals("SOUTH")){
                taux.setX(last.getX());
                taux.setY(last.getY()+1);
                taux.setBoard(board);
                taux.setTileState(TileState.AVAILABLE);
            }res.add(taux);
        }return res;
    }

    public void playCard(Usuario player, Card card, Tile tile) throws InvalidMoveException {
        Deck deck = deckService.getDeckFromPlayer(player);
        if(deck != null && deck.getCards().contains(card) &&
         availableTiles(card.getColor(), tile.getBoard()).stream().anyMatch(x->x.getX().equals(tile.getX()) && x.getY().equals(tile.getY()))) {
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

    public Deck deckFromPlayers(Usuario player){
        Deck deck=deckService.getDeckFromPlayer(player);
        return deck;
    } 
    public List<CardType> getAllCardTypes(){
        return deckService.AllCardTypes();
    }

    public Tile tileByCoords(Board board, Integer x, Integer y){
        Tile tile=tileService.findTileByCoordsAndBoard(board, x, y);
        return tile;
    }
                 
    public void save(Board board) {
        boardRepository.save(board);
    }

    public void generateVersusBoard(Board board, int players) {

        int numPlayers = board.getGame().getPlayers().size();
        int size = 0;

        if(numPlayers < 4){
            size = 7;
        }else if(numPlayers > 3){
            size = 9;
        }

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
