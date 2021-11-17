package org.springframework.samples.petclinic.statistics;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/List")
    public String getMethodName(ModelMap modelMap) {
        String rute="Statistic/List";
        Collection<Statistics> l= statisticsService.findAllStatics();
        modelMap.addAllAttributes(l);
        return rute;
    }
    
}
