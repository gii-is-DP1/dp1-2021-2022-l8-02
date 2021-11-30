package org.springframework.samples.endofline.Achievements;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="achievements")
public class Achievement extends BaseEntity{
    

    @NotNull 
    String name;

    @Enumerated(EnumType.STRING) 
    AchievementType type;

    @NotNull  
    String description;
    

}
