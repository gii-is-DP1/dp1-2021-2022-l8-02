package org.springframework.samples.endofline.energies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.energies.exception.DontUsePowerInTheSameRound;
import org.springframework.samples.endofline.energies.exception.DontUsePowerBeforeThirdRound;
import org.springframework.samples.endofline.game.Round;
import org.springframework.samples.endofline.game.RoundService;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class EnergyService {
    

    @Autowired 
    private EnergyRepository energyRepo;
    @Autowired
    private PowerService powerService;

    

    @Autowired
   RoundService roundService;

    public Energy getEnergyFromPlayer(Usuario user){
        return energyRepo.findEnergyByPlayerUsername(user.getUsername());
    
   }

    /*para dar un poder crear una energia con poder
     que haya cogido en jsp (mirar createGame de gameService)*/
   @Transactional
    public void initEnergy(List<Usuario> users, List<Power> powers){
        Map<Power, Boolean> p= new HashMap<>();
        for(Usuario u : users ){
        Energy energy= new Energy();
        energy.setCounter(3);
        energy.setUser(u);
        for (Power po: powers){
            p.put(po, false);
        }
        energy.setPowers(p);
        u.setEnergy(energy);
        save(energy);
        }
    }

    public void usePower(Usuario user, Integer powerId) throws DontUsePowerInTheSameRound, DontUsePowerBeforeThirdRound{
        Integer energy = getEnergyFromPlayer(user).getCounter();
        Map<Power, Boolean> powers = user.getEnergy().getPowers();
        System.out.println(powers);
        Round round = user.getTurn().getRound();
        if(round.getNumber()>2 ){
            if(getEnergyFromPlayer(user).getLastRound() != user.getTurn().getRound().getNumber()){
                if(energy <= 3 && energy >0){
                    if(powerId == 1){/*aceleron*/
                        powers.put(powerService.findById(1), true);
                    }else if(powerId == 2){/*frenazo*/
                        powers.put(powerService.findById(2), true);
                    }else if(powerId == 3){/*marcha atras*/
                        powers.put(powerService.findById(3), true);
                    }else if(powerId == 4){ /*gas extra*/
                        powers.put(powerService.findById(4), true);
                    }
                    energy-=1;
                }
            }else{
            throw new DontUsePowerInTheSameRound();
            }
        }
        else{
            throw new DontUsePowerBeforeThirdRound();
        }
        System.out.println(powers);
        Energy newEnergy = getEnergyFromPlayer(user);
        newEnergy.setCounter(energy);
        newEnergy.setLastRound(user.getTurn().getRound().getNumber());
        newEnergy.setPowers(powers);
        user.setEnergy(newEnergy);
        save(newEnergy);
        if( powerId == 4){
        roundService.refreshRound(user.getTurn().getRound().getGame(), user);
        }
    }
    public void allFalse(Usuario player){
        Map<Power, Boolean> map = player.getEnergy().getPowers();
        Set<Power> powers = map.keySet();
        for(Power p: powers){
            map.put(p, false);
        }
        Energy ene = player.getEnergy();
        ene.setPowers(map);
        player.setEnergy(ene);
        save(ene);
    }
  

    public void save(Energy energy) {
        energyRepo.save(energy);
    }


}
