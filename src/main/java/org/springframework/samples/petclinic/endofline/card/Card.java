package org.springframework.samples.petclinic.endofline.card;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "id")
    private CardType cardType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CardColor color;
    
}
