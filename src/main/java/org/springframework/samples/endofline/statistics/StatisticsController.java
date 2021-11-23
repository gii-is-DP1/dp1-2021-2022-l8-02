package org.springframework.samples.endofline.statistics;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    public static final String STATISTICS_LISTING="statistics/list";


    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    public String listStatistics(ModelMap model){
        model.addAttribute("statistics", statisticsService.findAll());
        return STATISTICS_LISTING;
    }
}