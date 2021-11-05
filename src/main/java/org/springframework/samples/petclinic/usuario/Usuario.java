package org.springframework.samples.petclinic.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

    @Column(name = "username")
	@NotEmpty
    private String username;

    @Column(name = "password")
	@NotEmpty
    private String password;

    @Column(name = "email")
	@NotEmpty
    private String email;

    public boolean isNew() {
		return this.id == null;
	}

    // public String getUsername() {
	// 	return this.username;
	// }

	// public String getPassword(){
    //     return this.password;
    // }

    // public String getEmail(){
    //     return this.email;
    // }

}
