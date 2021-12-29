package org.springframework.samples.endofline.card;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.board.Tile;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<CardType> findAllCardTypes() {
        return cardRepository.findAllCardTypes();
    }

    public CardType findCardTypeByIniciative(Integer iniciative) {
        return cardRepository.findCardTypeByIniciative(iniciative);
    }

    public CardType findCardTypeByName(String name) {
        return cardRepository.findCardTypeByName(name);
    }

    @Transactional
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Transactional
    public List<Card> autoColorAssignInitCards(int numPlayers){
        List<Card> list = new ArrayList<>();

        switch(numPlayers){
            case 1:
            case 2:
                init12(numPlayers, list);
                break;
            case 3:
                init3(numPlayers, list);
                break;
            case 4:
                init4(numPlayers, list);
                break;
            case 5:
                init5(numPlayers, list);
                break;
            case 6:
                init6(numPlayers, list);
                break;
            case 7:
                init7(numPlayers, list);
                break;
            case 8:
                init8(numPlayers, list);
                break;
        }

        return list;
    }

    public Direction calculateRotation(Tile toBeOccupied, Tile lastTileOccupied){
        Direction res = null;
         //Lo de marcha atras se podria hacer a la hora de llamar al getAdjacents()
        Double aux = Math.sqrt(toBeOccupied.getBoard().getTiles().size());
        Integer boardDimension = aux.intValue();
        Boolean oppositeX = Math.abs(toBeOccupied.getX()-lastTileOccupied.getX()) == boardDimension-1;
        Boolean oppositeY = Math.abs(toBeOccupied.getY()-lastTileOccupied.getY()) == boardDimension-1;
        
        if(toBeOccupied.getX() == lastTileOccupied.getX()){
            if(toBeOccupied.getY() > lastTileOccupied.getY()){
                res = Direction.SOUTH;
            }else{
                res = Direction.NORTH;
            }
        }else{
            if(toBeOccupied.getX() > lastTileOccupied.getX()){
                res = Direction.EAST;
            }else{
                res = Direction.WEST;
            }
        }
        
        if(oppositeX || oppositeY ){
            Integer auxi = (res.ordinal()+2)%4;
            res = Direction.values()[auxi];
        }return res;

    }

    //First card auxiliary init methods
    
    private void init8(int numPlayers, List<Card> list) {
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            switch(i){
                case 0:
                    card.setRotation(Direction.NORTH);
                    break;
                case 1:
                    card.setRotation(Direction.NORTH);
                    break;
                case 2:
                    card.setRotation(Direction.EAST);
                    break;
                case 3:
                    card.setRotation(Direction.EAST);
                    break;
                case 4:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 5:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 6:
                    card.setRotation(Direction.WEST);
                    break;
                case 7:
                    card.setRotation(Direction.WEST);
                    break;
            }
            save(card);
            list.add(card);
        }
    }

    private void init7(int numPlayers, List<Card> list) {
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            switch(i){
                case 0:
                    card.setRotation(Direction.NORTH);
                    break;
                case 1:
                    card.setRotation(Direction.NORTH);
                    break;
                case 2:
                    card.setRotation(Direction.EAST);
                    break;
                case 3:
                    card.setRotation(Direction.EAST);
                    break;
                case 4:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 5:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 6:
                    card.setRotation(Direction.WEST);
                    break;
            }
            save(card);
            list.add(card);
        }
    }

    private void init6(int numPlayers, List<Card> list) {
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            switch(i){
                case 0:
                    card.setRotation(Direction.NORTH);
                    break;
                case 1:
                    card.setRotation(Direction.NORTH);
                    break;
                case 2:
                    card.setRotation(Direction.EAST);
                    break;
                case 3:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 4:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 5:
                    card.setRotation(Direction.WEST);
                    break;
            }
            save(card);
            list.add(card);
        }
    }

    private void init5(int numPlayers, List<Card> list) {
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            switch(i){
                case 0:
                    card.setRotation(Direction.NORTH);
                    break;
                case 1:
                    card.setRotation(Direction.NORTH);
                    break;
                case 2:
                    card.setRotation(Direction.EAST);
                    break;
                case 3:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 4:
                    card.setRotation(Direction.SOUTH);
                    break;
            }
            save(card);
            list.add(card);
        }
    }

    private void init4(int numPlayers, List<Card> list) {
            for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            switch(i){
                case 0:
                    card.setRotation(Direction.NORTH);
                    break;
                case 1:
                    card.setRotation(Direction.EAST);
                    break;
                case 2:
                    card.setRotation(Direction.SOUTH);
                    break;
                case 3:
                    card.setRotation(Direction.WEST);
                    break;
            }
            save(card);
            list.add(card);
        }
    }

    private void init3(int numPlayers, List<Card> list) {
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            switch(i){
                case 0:
                    card.setRotation(Direction.WEST);
                    break;
                case 1:
                    card.setRotation(Direction.EAST);
                    break;
                case 2:
                    card.setRotation(Direction.NORTH);
            }
            save(card);
            list.add(card);
        }
    }

    private void init12(int numPlayers, List<Card> list) {
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            card.setRotation(Direction.NORTH);
            save(card);
            list.add(card);
        }
    }
    
}
