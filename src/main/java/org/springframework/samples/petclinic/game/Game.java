package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.dom4j.rule.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="game")
public class Game extends NamedEntity{
        @Column(name="winner")
        private Boolean winner;

        @Column(name="game_status")
        private String gameStatus;

        @Column(name="start_game")
        @DateTimeFormat(pattern ="HH:mm:ss")
        private LocalTime startGame;

        @Column(name="end_game")
        @DateTimeFormat(pattern= "HH:mm:ss")
        private LocalTime endGame;

        @Column(name="turn")
        private Integer turn;
}
