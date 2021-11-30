package org.springframework.samples.endofline.Achievements;

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
@Table(name="achievements")
public class Achievement extends BaseEntity{
    
    @OneToOne
    @NotNull
    Usuario user;

    String name;

    AchievementType type;
    
    String description;
    

}
