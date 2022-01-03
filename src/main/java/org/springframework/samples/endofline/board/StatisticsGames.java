package org.springframework.samples.endofline.board;

import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.endofline.usuario.Usuario;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "StatisticsGames")
public class StatisticsGames extends BaseEntity{
    
	@NotNull
    @OneToOne
    Usuario user;

    @NotNull
    @OneToOne
    Game game;

    @ElementCollection
    @MapKeyColumn(name="statisticsMapCard")
    Map<Card,Integer> map;

    Integer point;

    public Card getMaxCard(){
        Set<Card> setCards = map.keySet();
        Card cardMax = null;
        Integer val = 0;
        for (Card card : setCards){
            Integer valCard = map.get(card);
            if(map.get(card) > val){
                val = valCard;
                cardMax = card;
            }
        }
        return cardMax;
    }
}