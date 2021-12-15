package org.springframework.samples.endofline.energies;


import javax.persistence.Entity;
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
@Table(name="energies")
public class Energy extends BaseEntity{
    @NotNull
    Integer counter;

    @NotNull
    @OneToOne
    private Usuario user;
    
    @NotNull
    Powers powers;


}
