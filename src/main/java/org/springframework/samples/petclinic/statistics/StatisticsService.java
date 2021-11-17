package org.springframework.samples.petclinic.statistics;

import java.util.Collection;
import java.util.Comparator;

import javax.transaction.Transactional;

import org.ehcache.shadow.org.terracotta.statistics.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    
    @Autowired
    StatisticsRepository statisticsRepositor;

    @Transactional
    public Collection<Statistics> findAllStatics(){
        return statisticsRepositor.findAll();
    }

    @Transactional
    public Statistics findCollectionId(Integer id){
        return statisticsRepositor.findById(id).get();
    }
}
