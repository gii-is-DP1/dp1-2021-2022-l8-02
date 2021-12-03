package org.springframework.samples.endofline.power;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "powers")
public class Power extends BaseEntity{

    @NotNull
    String name;

    
}
