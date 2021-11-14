package org.springframework.samples.petclinic.endofline.game;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.endofline.board.Board;
import org.springframework.samples.petclinic.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @OneToOne
    @NotNull
    private Board board;
    
}
