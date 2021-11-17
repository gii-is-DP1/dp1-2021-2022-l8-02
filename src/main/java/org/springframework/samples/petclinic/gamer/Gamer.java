package org.springframework.samples.petclinic.gamer;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.endOnline.game.Game;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "gamer")
public class Gamer extends NamedEntity{

    @ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

    @ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

}