package org.springframework.samples.endofline.usuario;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.endofline.game.Turn;
import org.springframework.samples.endofline.achievements.Achievement;
import org.springframework.samples.endofline.energies.Energy;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario{

    @Id
    @Column(name = "username")
	@NotEmpty
    private String username;

    @Column(name = "password")
	@NotEmpty
    private String password;

    @Email
    @Column(name = "email")
	@NotEmpty
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private Set<Authorities> authorities;

   @OneToMany(cascade = CascadeType.ALL)
    private List<Achievement> achievements;

    @OneToOne(mappedBy = "usuario")
    private Turn turn;

    @OneToOne
    private Energy energy;

}
