package org.springframework.samples.endofline.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.endofline.game.Turn;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.endofline.achievements.Achievement;
import org.springframework.samples.endofline.energies.Energy;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario{

    @Id
    @Column(name = "username")
	@NotEmpty
    private String username;

    @Column(name = "password")
	@NotEmpty
    private String password;

    
    @Column(name = "passwordRepeat")
    private String passwordRepeat;

    @Email
	@NotEmpty
    @Column(name = "email")
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private Set<Authorities> authorities;
    
    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL)
    private List<Achievement> achievements;
    
    @OneToOne(mappedBy = "usuario")
    private Turn turn;
    
    @Column(name = "estado_partida")
    private Boolean gameEnded;
    
    @OneToOne
    private Energy energy;
    
    @ElementCollection
    List<Integer> inicialListCardsByPlayer;
    

    // Auditing
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    
}
