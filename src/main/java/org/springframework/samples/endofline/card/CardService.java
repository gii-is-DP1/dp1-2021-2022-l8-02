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
        for(int i = 0; i < numPlayers; i++){
            Card card = new Card();
            card.setCardType(findCardTypeByIniciative(-1));
            card.setColor(CardColor.values()[i]);
            card.setRotation(Direction.NORTH);
            save(card);
            list.add(card);
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
    
}
