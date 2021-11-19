package org.springframework.samples.petclinic.card;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cardTypes")
public class CardType extends BaseEntity {

    @NotEmpty
    @Column(name = "iniciative")
    private Integer iniciative;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Direction> directions;
    
}
