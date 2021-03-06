package org.springframework.samples.endofline.gameStorage;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.board.Board;
import org.springframework.samples.endofline.game.GameMode;
import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.endofline.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "storage")
public class GameStorage extends BaseEntity {
    
    
    @ElementCollection
    private List<String> players;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "game")
    private Board board;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    @ManyToOne(optional = true)
    private Usuario winner;





}
