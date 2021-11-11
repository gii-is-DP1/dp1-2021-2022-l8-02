package org.springframework.samples.petclinic.carta;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name= "carta")
public class Carta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    
    @Column(name= "inicitaiva")
    @NotEmpty
    private Integer iniciativa;

    @ElementCollection
    private List<Direccion> direcciones;

    @Enumerated(EnumType.STRING)
    @NotEmpty
    private Tipo type;

    public boolean isNew() {
		return this.id == null;
	}
}
