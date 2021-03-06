package org.springframework.samples.endofline.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tiles")
public class Tile extends BaseEntity {

    @NotNull
    @Column(name = "x")
    private Integer x;

    @NotNull
    @Column(name = "y")
    private Integer y;

    @NotNull
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private TileState tileState;

    @NotNull
    @ManyToOne
    private Board board;

    @OneToOne
    private Card card;
    
}
