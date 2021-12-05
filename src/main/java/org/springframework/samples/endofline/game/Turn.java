package org.springframework.samples.endofline.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.endofline.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "turns")
public class Turn extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "turn")
    @NotNull
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @ManyToOne
    @NotNull
    private Round round;

}
