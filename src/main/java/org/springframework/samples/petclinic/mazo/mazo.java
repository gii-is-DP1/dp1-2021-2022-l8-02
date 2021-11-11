package org.springframework.samples.petclinic.mazo;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.samples.petclinic.carta.Carta;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "mazo")
public class mazo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ElementCollection
    private List<Carta> direcciones;
}
