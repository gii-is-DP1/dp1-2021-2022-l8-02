package org.springframework.samples.endofline.puzzle;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.endofline.board.Tile;
import org.springframework.samples.endofline.card.CardType;
import org.springframework.samples.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "puzzlesTiles")
public class  PuzzleTile extends BaseEntity {

    private Integer puzzleId;

    private Integer x;
    
    private Integer y;

    @OneToOne
    private CardType cardType;
    
}
