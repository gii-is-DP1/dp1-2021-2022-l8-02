package org.springframework.samples.endofline.power;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerService {
    
    @Autowired
    PowerRepository powerRepo;

   public Power findByName(String name){
       return powerRepo.findByName(name);
   }

    @Transactional
    public Power findById(Integer id){
        return powerRepo.findById(id).get();
    }

    @Transactional
    public List<Power> findAll(){
        return powerRepo.findAll();
    }

    public List<String> getPowerNames(){
        List<String> out = new ArrayList<>();
        for(Power p : findAll()){
            p.getName();
            out.add(p.getName());
        }
        return out;
    }
    
}
