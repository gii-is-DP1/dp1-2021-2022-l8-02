package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @OneToMany
    @ElementCollection
    private List<Usuario> players = new ArrayList<>();

    @NotEmpty
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "hidden")
    private Boolean hidden;

    // @NotNull Si no se comenta falla al crear la sala del juego
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "game")
    private Board board;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    @Enumerated(EnumType.STRING)
    private GameState GameState;

    @OneToOne
    private Round round;

    /*
    private List<Power> power;*/

}
