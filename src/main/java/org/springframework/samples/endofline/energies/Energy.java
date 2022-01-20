package org.springframework.samples.endofline.energies;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="energies")
public class Energy extends BaseEntity{

    @NotNull
    Integer counter;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "energy")
    @NotNull
    @JoinColumn(name = "usuario")
    private Usuario user;
    
    @NotNull
    @ElementCollection
    @MapKeyColumn(name="powerMap")
    Map<Power, Boolean> powers;

    private Integer lastRound;
}
