package org.springframework.samples.endofline.energies;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.game.Round;
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
    
    @OneToMany(cascade= CascadeType.ALL)
    List<Power> powers;



    private Integer LastRound;
}
