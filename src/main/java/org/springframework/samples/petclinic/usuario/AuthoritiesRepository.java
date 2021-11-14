package org.springframework.samples.petclinic.usuario;

import org.springframework.data.repository.CrudRepository;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
