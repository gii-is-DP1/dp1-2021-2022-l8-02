package org.springframework.samples.endofline.energies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.game.Game;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.power.powerRepository;
import org.springframework.samples.endofline.power.powerService;
import org.springframework.samples.endofline.usuario.Usuario;

public class EnergyService {
    

    @Autowired 
    private EnergyRepository energyRepo;

    private powerService powerService;

    public Energy getEnergyFromPlayer(Usuario user){
       Energy ene= energyRepo.findEnergyByPlayerUsername(user.getUsername());
        return ene;
   }

    public Integer findEnergyOfPlayerInGame(Usuario user){
        Integer energy=0;
        

        return energy;
    }


    /*para dar un poder crear una energia con poder
     que haya cogido en jsp (mirar createGame de gameService)*/

    public Energy initEnergy(Usuario user){
        Energy energy= new Energy();
        energy.setUser(user);
        energy.setCounter(3);
        return energy;
    }

    public void usePower(Usuario user, Power power){
        Integer energy = findEnergyOfPlayerInGame(user);
        if(energy <= 3 && energy >0){
            if(power.getId()==1){

            }else if(power.getId()==2){

            }else if(power.getId()==3){

            }else if(power.getId()==4){

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
