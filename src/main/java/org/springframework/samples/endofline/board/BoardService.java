package org.springframework.samples.endofline.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.exceptions.InvalidMoveException;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;

import org.springframework.samples.endofline.card.CardType;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
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


    public void playCard(Usuario player, Card card, Tile tile) throws InvalidMoveException {
        Deck deck = deckService.getDeckFromPlayer(player);
        if(deck != null && deck.getCards().contains(card) && checkPlayableCard(card,tile)) {
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

    //@author Migue
    public boolean checkPlayableCard(Card card, Tile tile) {
        boolean tlState = tile.getTileState()==TileState.AVAILABLE?true:false;
        return tlState && true;
    }
/*
   public TileState getTileState(Board board, Tile tile){
        TileState t = TileState.FREE;
        if(tile.getCard()!=null){
            t = TileState.TAKEN;
        }else{
            t = maybeTileBorder(board, tile);
        }
    }

    private TileState maybeTileBorder(Board board, Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        Integer aux = board.getTiles().size();
        //Last Possible Tile Index
        Double lpti = Math.sqrt(aux.doubleValue())-1;
        if(x>0 && x<lpti && y>0 && y<lpti){
            for(int e = 0;e < 4;e++){
                //Clockwise, first tile I check is the above one
                if(tileByCoords(board, x, y-1).getTileState()==TileState.TAKEN && tileByCoords(board, x, y).getCard().getCardType().getDirections().contains("SUR"));
            }
        }
        return null;
    }
*/

    //@Author Migue: Algoritmo recursivo para encontrar la dirección a la que apunta el camino
    public String findFacingDirection(Board board, Tile tile, CardColor color){
      //Caso base
      String laneCurrentDirection = "";
      int startDirection = 1;
      int res = 69;
      int currentDirection = 69;
      String restr = "";
      List<String> dirs = new ArrayList<>();
      dirs.add("NORTH"); //North = 1, East = 2, South = 3, West = 4
      dirs.add("EAST"); //North = 1, East = 2, South = 3, West = 4
      dirs.add("SOUTH"); //North = 1, East = 2, South = 3, West = 4
      dirs.add("WEST"); //North = 1, East = 2, South = 3, West = 4
      if(isLastTile(board,tile,color)) {
        res = redirectioner(tile.getCard().getCardType(),laneCurrentDirection,currentDirection);
        switch(res){
            case 1:
                restr = "NORTH";
                break;
            case 2: 
                restr = "EAST";
                break;
            case 3:
                restr = "SOUTH";
                break;
            case 4:
                restr = "WEST";
                break;
        }
      }else if(tile.getCard().getCardName().equals("start")){
        Tile aux = tileByCoords(board, tile.getX(), tile.getY()+1);
        restr = findFacingDirection(board, aux, color);
      }else{
          restr = redirectioner(tile.getCard().getCardType(), laneCurrentDirection, currentDirection); //DECIDIR SI INT O STRING, MAS O MENOS PINTA MANERAS.
      }
      return restr;
    }

    private int redirectioner(CardType type, String laneCurrentDirection, int intLane){
        String res = laneCurrentDirection;
        Integer resInt = intLane;
        switch(type.getName()){
            case "0":
                break;
            case "1":
                break;
            case "2":
                //if(laneCurrentDirection.equals("NORTH")) res = "EAST";
                if(intLane==4){
                    resInt = 1;
                }else{
                    resInt = intLane+1;
                }
            case "2b":
                if(intLane==1){
                    resInt = 4;
                }else{
                    resInt = intLane-1;
                }
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
        }return resInt;
    }

    private boolean isLastTile(Board board, Tile tile, CardColor color) {
        int x = tile.getX();
        int y = tile.getY();
        int cont = 0;
        Tile above = tileByCoords(board, x, y-1);
        Tile left = tileByCoords(board, x+1, y);
        Tile below = tileByCoords(board, x, y-1);
        Tile right = tileByCoords(board, x-1, y);
        Set<Tile> tiles = new HashSet<>();
        tiles.add(above);
        tiles.add(left);
        tiles.add(right);
        tiles.add(below);
        for(Tile t:tiles){
            if(notOccupiedByPlayer(t, color)) cont++;
        }
        return cont==3?true:false;
    }

    private Boolean notOccupiedByPlayer(Tile tile, CardColor color){
        return (tile.getCard()==null || tile.getCard().getColor()!=color)?true:false;
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
