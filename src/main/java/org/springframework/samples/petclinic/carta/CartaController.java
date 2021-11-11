package org.springframework.samples.petclinic.carta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cartas")
public class CartaController {
    
    
	public static final String CARTA_LISTING="cartas/CartasListing";
    @Autowired
    CartaService cartaService;

    @GetMapping
    public String listCartas(ModelMap model){
        model.addAttribute("cartas", cartaService.findAll());
        return CARTA_LISTING;
    }

}
