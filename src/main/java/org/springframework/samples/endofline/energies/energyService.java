package org.springframework.samples.endofline.energies;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.power.powerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class EnergyService {
    

    @Autowired 
    private EnergyRepository energyRepo;

    private powerService powerService;
    private UsuarioService userService;

    public Energy getEnergyFromPlayer(Usuario user){
        return energyRepo.findEnergyByPlayerUsername(user.getUsername());
    
   }

   


    /*para dar un poder crear una energia con poder
     que haya cogido en jsp (mirar createGame de gameService)*/
   @Transactional
    public void initEnergy(List<Usuario> users){
        for(Usuario u : users ){
        Energy energy= new Energy();
        energy.setCounter(3);
        energy.setUser(u);
        u.setEnergy(energy);
        save(energy);
    }
       
    }

    public void usePower(Usuario user, String powerName){
        Integer energy = getEnergyFromPlayer(user).getCounter();
        if(energy <= 3 && energy >0){
            if(powerService.findByName(powerName).getId()==1){

            }else if(powerService.findByName(powerName).getId()==2){

            }else if(powerService.findByName(powerName).getId()==3){

            }else if(powerService.findByName(powerName).getId()==4){

            }
            energy-=1;
        }
        Energy newEnergy= new Energy();
        newEnergy.setCounter(energy);
        newEnergy.setUser(user);
        save(newEnergy);

    }
   /* public void usePower(Usuario user){
        Energy energy = getEnergyFromPlayer(user);
        Integer num= energy.getCounter();
        if(num <= 3 && num >0){
            switch(energy.getPowers()) {
                case ACELERON:

                break;
                case FRENAZO:
                
                    break;
                case GAS_EXTRA:
                
                break;
                case MARCHA_ATRAS:
                
                break;
            }
            num-=1;
        }
        Energy newEnergy= new Energy();
        newEnergy.setCounter(num);
        newEnergy.setUser(user);
        save(newEnergy);

    }*/

    public void save(Energy energy) {
        energyRepo.save(energy);
    }


}
