package org.springframework.samples.petclinic.endofline.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.endofline.board.Board;
import org.springframework.samples.petclinic.endofline.model.BaseEntity;
import org.springframework.samples.petclinic.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    // @ElementCollection
    // @OneToMany(mappedBy = "username")
    // List<Usuario> players;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "hidden")
    private Boolean hidden;

    // @NotNull Si no se comenta falla al crear la sala del juego
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "game")
    private Board board;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gameMode;

    @Enumerated(EnumType.STRING)
    private GameState GameState;
    
}
