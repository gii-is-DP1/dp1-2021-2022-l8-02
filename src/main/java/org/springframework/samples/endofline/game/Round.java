package org.springframework.samples.endofline.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.energies.Energy;
import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.endofline.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rounds")
public class Round extends BaseEntity{

    @OneToMany
    @ElementCollection
    private List<Turn> turns = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "round")
    @NotNull
    private Game game;

    @OneToMany
    @ElementCollection
    @NotNull
    private List<Usuario> players = new ArrayList<>();

    @NotNull
    private Integer number = 1;
 
    
}
