package org.springframework.samples.petclinic.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.endOnline.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "boards")
public class Board extends BaseEntity {

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
    private List<Tile> tiles;

    @OneToOne
    private Game game;
}
