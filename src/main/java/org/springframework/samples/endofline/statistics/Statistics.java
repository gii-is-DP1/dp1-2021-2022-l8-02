package org.springframework.samples.endofline.statistics;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder.ObtainVia;


@Entity
@Getter
@Setter
@Table(name = "statistics")
public class Statistics extends BaseEntity{

    @OneToOne
	@NotNull
	Usuario usuario;

	Integer numPlayers;

	Integer numGames;

	Integer duration;

}