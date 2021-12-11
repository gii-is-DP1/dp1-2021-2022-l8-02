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

    public void initEnergy(Game game){
        List<Usuario> players = new ArrayList<>(game.getPlayers());
        for(int i = 0; i < players.size(); i++){
            Energy energyFromPlayer= getEnergyFromPlayer(players.get(i));
            energyFromPlayer.setCounter(3);
        }
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
        save(newEnergy);

    }

    public void save(Energy energy) {
        energyRepo.save(energy);
    }


}
