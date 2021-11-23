package org.springframework.samples.endofline.statistics;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    
    @Autowired
    StatisticsRepository statisticsRepositor;

    @Transactional
    public Collection<Statistics> findAll(){
        return statisticsRepositor.findAll();
    }


    @Transactional
    public Statistics findByUser(Usuario usuario){
        return statisticsRepositor.findStatisticsByUser(usuario);
    }

    public void save(Statistics s){
        statisticsRepositor.save(s);
    }



    // @Transactional
    // public Statistics findOne(String username){
    //     Integer numOfGames = statisticsRepositor.getNumberOfGames(username);

    //     Statistics result = new Statistics();
    //     result.setNumGames(numOfGames);
    //     statisticsRepositor.save(result);

    //     return result;




}

