package org.springframework.samples.endofline.energies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.endofline.usuario.Usuario;

public interface EnergyRepository extends CrudRepository<Energy, Integer> {
    
    @Query("SELECT energy FROM Energy energy WHERE energy.user.username=?1")
    Energy findEnergyByPlayerUsername(String username);
    


}
