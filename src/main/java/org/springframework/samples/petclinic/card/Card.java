package org.springframework.samples.petclinic.card;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card {
    
    @OneToOne
    @JoinColumn(name = "id")
    private CardType cardType;

    @NotNull
    @Enumerated
    private CardColor color;
}
