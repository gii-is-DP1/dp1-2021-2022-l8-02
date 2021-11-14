package org.springframework.samples.petclinic.endofline.card;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "decks")
public class Deck extends BaseEntity {

    @ElementCollection
    @Column(name = "cards")
    private List<Card> cards;
    
}
