package org.springframework.samples.petclinic.statistics;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.endOnline.game.Game;
import org.springframework.samples.petclinic.gamer.Gamer;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "statistics")
public class Statistics extends NamedEntity{

    @ManyToOne
	@JoinColumn(name = "gamer_id")
	private Gamer gamer;

    @ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

}