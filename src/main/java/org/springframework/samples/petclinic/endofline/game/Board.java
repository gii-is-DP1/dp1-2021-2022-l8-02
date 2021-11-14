package org.springframework.samples.petclinic.endofline.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.endofline.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "boards")
public class Board extends BaseEntity {

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Tile> tiles;
    
}
