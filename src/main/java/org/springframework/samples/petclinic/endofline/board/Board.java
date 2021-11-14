package org.springframework.samples.petclinic.endofline.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    
    @NotNull
    @Positive
    private Integer size;
}
