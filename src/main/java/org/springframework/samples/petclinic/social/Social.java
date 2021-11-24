/*package org.springframework.samples.petclinic.social;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.usuario.Usuario;

@Entity
@Getter
@Setter
@Table(name = "friends")
public class Social {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Usuario usuario;

    @ManyToOne
    private Set<Usuario> friendsList;


    
    
}
*/