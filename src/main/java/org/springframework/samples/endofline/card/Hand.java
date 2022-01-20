package org.springframework.samples.endofline.card;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hand")
public class Hand extends BaseEntity{

    @NotNull
    @OneToOne
    Deck deck;

    @OneToMany
    @ElementCollection
    List<Card> cards;

    @OneToMany
    @ElementCollection
    List<Card> dismissCardsList;
}
