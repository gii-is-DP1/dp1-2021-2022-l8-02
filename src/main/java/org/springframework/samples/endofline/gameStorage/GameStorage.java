package org.springframework.samples.endofline.gameStorage;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "storage")
public class GameStorage extends BaseEntity {
    
    @OneToMany
    List<Game> games;


}
