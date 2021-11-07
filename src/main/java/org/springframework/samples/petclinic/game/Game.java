package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
        @NotEmpty
        private Boolean winner;

        @NotNull
        @Column(name = "game_status")
        @Enumerated(EnumType.STRING)
        private gameStatus game_status;

        @Column(name="start_game")
        @NotEmpty
        @DateTimeFormat(pattern ="HH:mm:ss")
        
        private LocalTime startGame;

        @Column(name="end_game")
        @NotEmpty
        @DateTimeFormat(pattern= "HH:mm:ss")
        private LocalTime endGame;

        @Column(name="turn")
        @NotEmpty
        private Integer turn;
}
