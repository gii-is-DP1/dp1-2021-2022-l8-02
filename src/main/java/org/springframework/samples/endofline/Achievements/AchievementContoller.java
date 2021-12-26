package org.springframework.samples.endofline.Achievements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AchievementContoller {
    
    public static final String ACHIEVEMENT_LIST="Achievements";
    public static final String USER_ACHIEVEMENT="/user/achievements";

    @Autowired
    AchievementService achievementService;

    @Autowired
    UsuarioService userService;

    @GetMapping
    public String listAchievement(Model model){
        model.addAttribute("achievements", achievementService.findAll());
        return ACHIEVEMENT_LIST;
    }

   /* @GetMapping("/user/{userName}/achievements")
    public String AchievementOfUser(@PathVariable("userName") String userName, Model model){
        Achievement achievements= achievementService.findAchievementsByUser(userService.fidByUsername(userName).get());
        model.addAttribute("achievementOfUser", achievements);
        return USER_ACHIEVEMENT;
    }*/



}
