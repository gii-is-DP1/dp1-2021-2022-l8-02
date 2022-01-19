package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.board.Path;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.board.TileService;
import org.springframework.samples.endofline.board.TileState;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.card.HandService;
import org.springframework.samples.endofline.energies.EnergyService;
import org.springframework.samples.endofline.game.exceptions.DuplicatedGameNameException;
import org.springframework.samples.endofline.game.exceptions.GameNotFoundException;
import org.springframework.samples.endofline.game.exceptions.TwoPlayersAtLeastException;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private GameRepository gameRepository;
    private BoardService boardService;
    private DeckService deckService;
    private TileService tileService;
    private CardService cardService;
    private HandService handService;
    private RoundService roundService;
    private EnergyService energyService;
    private PowerService powerService;

    private UsuarioService userService;

    @Autowired
    public GameService(PowerService powerService, EnergyService energyService, GameRepository gameRepository, BoardService boardService, DeckService deckService, TileService tileService, CardService cardService, RoundService roundService, HandService handService, UsuarioService userService) {


        this.gameRepository = gameRepository;
        this.boardService = boardService;
        this.deckService = deckService;
        this.tileService = tileService;
        this.cardService = cardService;
        this.handService = handService;
        this.roundService = roundService;
        this.energyService = energyService;
        this.powerService = powerService;
        this.userService = userService;

    }

    public Collection<Game> getGames() {
        return gameRepository.findAll();
    }
   

    public List<Game> getVersusGames() {
        return gameRepository.findByGameMode(GameMode.VERSUS);
    }
   

    @Transactional
    public void save(Game game){
        gameRepository.save(game);
    }

    public Game findGame(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()) {
            return game.get();
        }
        throw new GameNotFoundException();
    }

    @Transactional
    public void createGame(Game game) throws DuplicatedGameNameException {
        game.setGameState(GameState.LOBBY);

        Game otherGame = gameRepository.findByName(game.getName());
        if(otherGame != null)   throw new DuplicatedGameNameException();

        gameRepository.save(game);
    }

    public Game getGameByPlayer(Usuario player) {
        return gameRepository.getGameByPlayerUsername(player.getUsername());
    }

    @Transactional
    public void joinGame(Game game, Usuario player) {
        if(game.getGameMode() != GameMode.VERSUS && game.getPlayers().size() >= 1)  return;
        leaveGame(player);
        game.getPlayers().add(player);
        gameRepository.save(game);
    }

    @Transactional
    public void leaveGame(Usuario player) {
        Game currentGame = gameRepository.getGameByPlayerUsername(player.getUsername());
        if(currentGame != null) {
            currentGame.getPlayers().remove(player);
            if(currentGame.getPlayers().size() == 0) {
                gameRepository.delete(currentGame);
            } else {
                gameRepository.save(currentGame);
            }
        }
    }

    // public Map<Usuario,List<Integer>> diccConteinsAll(Map<Usuario,List<Integer>> dicc){

    // }
    // public Map<Usuario,List<Integer>> turnsForPlayer(List<Usuario>players){
    //     Map<Usuario,List<Integer>> dicc= new HashMap<>();

    // }

    // public List<Integer> getFirstRoundInitiatives(Map<Usuario, List<Integer> m){
    //     List<Integer> res = new ArrayList<>();
    //     for(List<Integer> ls:m.values()){
    //         if(ls.size() == 0){
    //             res.add(ls.get(0));
    //         }
    //         else{
    //             res.add(ls.get(ls.size()-1));
    //         }
    //     }Collections.sort(res);
    //     return res;
    // }

    @Transactional
    public void startGame(Game game) throws TwoPlayersAtLeastException {
        if(game.getPlayers().size()==1 && game.getGameMode() == GameMode.VERSUS) {
            throw new TwoPlayersAtLeastException();
        }

        Board board = new Board();
        board.setGame(game);
        boardService.save(board);

        switch(game.getGameMode()) {
            case PUZZLE:
                boardService.generatePuzzleBoard(board);
                energyService.initEnergy(game.getPlayers(), powerService.findAll());
                break;
            case SOLITAIRE:
                boardService.generateSolitaireBoard(board);
                energyService.initEnergy(game.getPlayers(), powerService.findAll());
                break;
            default:
            /*INICIALIZAR ENERGIA A CADA JUGADOR*/
            if(game.getPlayers().size()==1){
                throw new TwoPlayersAtLeastException();
            }
                energyService.initEnergy(game.getPlayers(), powerService.findAll());
                boardService.generateVersusBoard(board);
        }

        for(Integer i = 0; i < game.getPlayers().size(); i++){
            Deck deck = deckService.generateDefaultDeck(game.getPlayers().get(i), CardColor.values()[i]);
            handService.generateDefaultHand(deck);
        }

        userService.setStatFalse(game.getPlayers());

        Round round = new Round();
        round.setGame(game);

        //Metodo para decidir a inicio que usuarios, juegan.(Crear un diccionario donde se guarde jugador y iniciativa, si la iniciativa 
        //es igual entonces )
        

        
        for(Usuario u: game.getPlayers()){
            u.setInicialListCardsByPlayer(new ArrayList<>());
            userService.save(u);
        }

        List<Usuario> usersList = new ArrayList<>();
        List<Integer> firstRoundInitiatives = roundService.generateInicialOrderByPlayer(game.getPlayers());
            for(Integer e: firstRoundInitiatives){
                List<Usuario> auxLs = new ArrayList<>();
                for(Usuario u: game.getPlayers()){
                    //Sacariamos del dicc la lista correspondiente a cada usuario, le hariamos un size y lo sustituiriamos por 0.
                    if(u.getInicialListCardsByPlayer().get(0)==e){
                        auxLs.add(u);
                    }
                }
            Collections.shuffle(auxLs);
            usersList.addAll(auxLs);
        }
        round.setPlayers(new ArrayList<>());
        round.getPlayers().addAll(usersList);
        roundService.save(round);
        
       
        //Carta prueba partidas Versus con 1 persona 
        Card sPrueba = new Card();
        sPrueba.setCardType(cardService.findCardTypeByIniciative(-1));
        sPrueba.setColor(CardColor.GREEN);
        cardService.save(sPrueba);

        if(game.getGameMode() == GameMode.VERSUS){
            List<Usuario> numplayers = new ArrayList<>(game.getPlayers());
            List<Card> cardList = new ArrayList<>(cardService.autoColorAssignInitCards(numplayers.size()));
            if(numplayers.size() < 2){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardForLess3Players(board, cardList.get(0), sPrueba);
            }else if(numplayers.size() == 2){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardForLess3Players(board, cardList.get(0), cardList.get(1));
            }else if(numplayers.size() == 3){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor3Players(board, cardList.get(0), cardList.get(1), cardList.get(2));
            }else if(numplayers.size() == 4){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor4Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3));
            }else if(numplayers.size()== 5){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor5Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4));
            }else if(numplayers.size() == 6){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor6Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4), cardList.get(5));
            }else if(numplayers.size() == 7){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor7Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4), cardList.get(5), cardList.get(6));
            }else if(numplayers.size()== 8){
                roundService.generateTurnsByPlayers(round, numplayers);
                tileService.setFirstCardFor8Players(board, cardList.get(0), cardList.get(1), cardList.get(2), cardList.get(3), cardList.get(4), cardList.get(5), cardList.get(6), cardList.get(7));
            }

            
            
            
        }else{
            roundService.generateTurnsByPlayers(round, game.getPlayers());
        }
        game.setRound(round);
        boardService.save(board);
        game.setGameState(GameState.PLAYING);
        gameRepository.save(game);


    }

    public List<Game> getGameByState(GameState state) {
        return gameRepository.getGameByGameState(state);
    }

    public List<Usuario> checkLostVS(Game game){
        List<Usuario> out = new ArrayList<>();
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        for(Usuario p : players){
            Path path = game.getBoard().getPaths().get(deckService.getDeckFromPlayer(p).getCards().get(0).getColor().ordinal());
            List<Tile> occupiedTiles = path.getOccupiedTiles();
            Tile lastTile = occupiedTiles.get(occupiedTiles.size() - 1);
            List<Tile> availableTiles = boardService.getAdjacents(lastTile, p, path);
            if(deckService.getDeckFromPlayer(p).getCards().size() == 0 || availableTiles.stream().allMatch(x -> tileService.findTileByCoordsAndBoard(game.getBoard(), x.getX(), x.getY()).getCard() != null)){
                out.add(p);
            }
        }
        return out;
    }

    public Boolean checkLostPuzzle(Game game){
        Boolean out = null;   //O hacer un string donde indique, si ha ganado a perdido o ni a ganado ni a perdido;
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        Long allTileTakenOfBoard= 25L;
        for(Usuario p : players){
            Path path = game.getBoard().getPaths().get(deckService.getDeckFromPlayer(p).getCards().get(0).getColor().ordinal());
            List<Tile> occupiedTiles = path.getOccupiedTiles();
            Tile lastTile = occupiedTiles.get(occupiedTiles.size() - 1);
            List<Tile> availableTiles = boardService.getAdjacents(lastTile, p, path);
            Long contTileTaken= game.getBoard().getTiles().stream().filter(x->x.getTileState().equals(TileState.TAKEN)).count();
            if(contTileTaken == allTileTakenOfBoard){
                out= false;
            }
            else if(availableTiles.stream().allMatch(x -> tileService.findTileByCoordsAndBoard(game.getBoard(), x.getX(), x.getY()).getCard() != null)){
                out= true;
            }
        }
        return out;
    }

    public Boolean checkDrawVS(Game game){
        Boolean out = true;
        List<Usuario> restPlayers = new ArrayList<>(game.getPlayers());
        for(int i = 0; i < restPlayers.size(); i++){
            Path path = game.getBoard().getPaths().get(deckService.getDeckFromPlayer(restPlayers.get(i)).getCards().get(0).getColor().ordinal());
            List<Tile> occupiedTiles = path.getOccupiedTiles();
            Tile lastTile = occupiedTiles.get(occupiedTiles.size() - 1);
            List<Tile> availableTiles = boardService.getAdjacents(lastTile, restPlayers.get(i), path);
            if(availableTiles.stream().allMatch(x -> tileService.findTileByCoordsAndBoard(game.getBoard(), x.getX(), x.getY()).getCard() != null)){
                out = true & out; 
            }else{
                out = false;
            }
        }
        return out;
    }

    @Transactional
    public void endGame(Game game){
        game.setGameState(GameState.ENDED);
        gameRepository.save(game);
    }


}
