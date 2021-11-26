package org.springframework.samples.endofline.statistics;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.endofline.usuario.Usuario;


public interface StatisticsRepository extends CrudRepository<Statistics, Integer>{
    
    public Collection<Statistics> findAll();
    @Query("SELECT statistics FROM Statistics statistics WHERE statistics.usuario=?1")
    public Statistics findStatisticsByUser(Usuario user);
    


}