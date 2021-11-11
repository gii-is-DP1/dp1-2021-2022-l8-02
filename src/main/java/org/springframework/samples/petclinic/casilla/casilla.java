package org.springframework.samples.petclinic.casilla;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="casilla")
public class casilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name="columna")
    @NotEmpty
    private Integer columna;

    @Column(name="fila")
    @NotEmpty
    private Integer fila;

    @Enumerated(EnumType.STRING)
    @NotEmpty
    private estadoCasilla estado_casilla;
    

}
