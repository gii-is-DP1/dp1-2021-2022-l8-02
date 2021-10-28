package org.springframework.samples.petclinic.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String listUsuarios(ModelMap model){
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/UsuariosListing";
    }
}
