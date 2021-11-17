package org.springframework.samples.petclinic.statistics;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistics, Integer>{
    public Collection<Statistics> findAll();
}