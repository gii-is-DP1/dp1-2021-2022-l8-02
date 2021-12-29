package org.springframework.samples.endofline.energies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.energies.exception.DontUsePowerInTheSameRound;
import org.springframework.samples.endofline.game.GameService;
import org.springframework.samples.endofline.game.Round;
import org.springframework.samples.endofline.game.RoundService;
import org.springframework.samples.endofline.game.TurnService;
import org.springframework.samples.endofline.power.Power;
import org.springframework.samples.endofline.power.PowerService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class EnergyService {
    

    @Autowired 
    private EnergyRepository energyRepo;
    @Autowired
    private PowerService powerService;
    private UsuarioService userService;
    private RoundService roundService;


    private GameService gameService;

    @Autowired
    private TurnService turnService;

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

    public void usePower(Usuario user, Integer powerId) throws DontUsePowerInTheSameRound{
        Integer energy = getEnergyFromPlayer(user).getCounter();
        Map<Power, Boolean> powers = user.getEnergy().getPowers();
        System.out.println(powers);
        Round round = user.getTurn().getRound();
        if(round.getNumber()>2 && getEnergyFromPlayer(user).getLastRound() != user.getTurn().getRound().getNumber()){
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
        System.out.println(powers);
        Energy newEnergy = getEnergyFromPlayer(user);
        newEnergy.setCounter(energy);
        newEnergy.setLastRound(user.getTurn().getRound().getId());
        newEnergy.setPowers(powers);
        user.setEnergy(newEnergy);
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
