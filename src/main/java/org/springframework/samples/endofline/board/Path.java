package org.springframework.samples.endofline.board;

import lombok.Getter;
import lombok.Setter;

import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.model.BaseEntity;
import javax.persistence.Table;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "paths")
public class Path extends BaseEntity{
    
    private CardColor color;

    @OneToMany
    @ElementCollection
    private List<Tile> occupiedTiles;

    @NotNull
    @ManyToOne
    private Board board;
}
