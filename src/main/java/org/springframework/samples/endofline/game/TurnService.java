package org.springframework.samples.endofline.game;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.BoardService;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    @Autowired
    TurnRepository turnRepository;

    @Autowired
    RoundService roundService;

    @Autowired
    PowerService powerService;

    @Autowired
    GameService gameService;

    @Autowired
    DeckService deckService;

    @Autowired
    BoardService boardService;

    public Collection<Turn> getTurns() {
        return turnRepository.findAll();
    }

    @Transactional
    public void save(Turn turn) {
        turnRepository.save(turn);
    }

    @Transactional
    public void delete(Turn turn) {
        turnRepository.delete(turn);
    }

    public Turn getByUsername(String username) {
        return turnRepository.getTurnByUsername(username);
    }

    

    // public void cardCounter(Usuario player, Game game, Map<Power, Boolean> powers) {
    //     Integer count = getByUsername(player.getUsername()).getCardCounter();
    //     count += 1;
    //     Turn t = player.getTurn();
    //     t.setCardCounter(count);
    //     save(t);
    //     player.setTurn(t);
    //     Path p = game.getBoard().getPaths().get(deckService.getDeckFromPlayer(player).getCards().get(0).getColor().ordinal());
    //     List<Tile> occupiedTiles = p.getOccupiedTiles();
    //     Tile lastTile = occupiedTiles.get(occupiedTiles.size() - 1);
    //     List<Tile> availableTiles = boardService.getAdjacents(lastTile, player, p);
    //     if(player.getTurn().getRound().getNumber() == 1){
    //         return;
    //     }
    //     if (player.getTurn().getRound().getNumber() == 1 || powers.get(powerService.findById(2)).booleanValue() == true) {
    //         roundService.refreshRound(game, player, availableTiles);
    //     } else if (powers.get(powerService.findById(1)).booleanValue() == true) {
    //         if (player.getTurn().getCardCounter() == 3) {
    //             roundService.refreshRound(game, player, availableTiles);
    //         }
    //     } else if (player.getTurn().getRound().getNumber() >= 2) {
    //         if (player.getTurn().getCardCounter() == 2) {
    //             roundService.refreshRound(game, player, availableTiles);
    //         }
    //     }

    // }
}
