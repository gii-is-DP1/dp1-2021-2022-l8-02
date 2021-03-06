package org.springframework.samples.endofline.card;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.endofline.model.BaseEntity;
import org.springframework.samples.endofline.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "decks")
public class Deck extends BaseEntity {

    @OneToMany
    @ElementCollection
    @Column(name = "cards")
    private List<Card> cards;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario user;
    

    private Integer count=0; //para ver las veces usado el cambio de mano
}
