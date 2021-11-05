package org.springframework.samples.petclinic.usuario;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    public static final String USUARIOS_FORM="usuarios/createOrUpdateUsuariosForm";
	public static final String USUARIOS_LISTING="usuarios/UsuariosListing";

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String listUsuarios(ModelMap model){
        model.addAttribute("usuarios", usuarioService.findAll());
        return USUARIOS_LISTING;
    }

    @GetMapping("/{id}/edit")
	public String editUsuario(@PathVariable("id") int id,ModelMap model) {
		Optional<Usuario> usuario=usuarioService.findById(id);
		if(usuario.isPresent()) {
			model.addAttribute("usuario",usuario.get());
			return USUARIOS_FORM;
		}else {
			model.addAttribute("message","We cannot find the user you tried to edit!");
			return listUsuarios(model);
		}
	}

	@PostMapping("/{id}/edit")
	public String editUsuario(@PathVariable("id") int id, @Valid Usuario modifiedDisease, BindingResult binding, ModelMap model) {
		Optional<Usuario> usuario=usuarioService.findById(id);
		if(binding.hasErrors()) {			
			return USUARIOS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedDisease, usuario.get(), "id");
			usuarioService.save(usuario.get());
			model.addAttribute("message","User updated succesfully!");
			return listUsuarios(model);
		}
	}

	@GetMapping("/{id}/delete")
	public String deleteUsuario(@PathVariable("id") int id,ModelMap model) {
		Optional<Usuario> usuario=usuarioService.findById(id);
		if(usuario.isPresent()) {
			usuarioService.delete(usuario.get());
			model.addAttribute("message","The user was deleted successfully!");
			return listUsuarios(model);
		}else {
			model.addAttribute("message","We cannot find the user you tried to delete!");
			return listUsuarios(model);
		}
	}

}
