package org.springframework.samples.petclinic.usuario;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    public static final String USUARIOS_FORM = "usuarios/DeleteOrUpdateUsuariosForm";
	public static final String USUARIOS_LISTING = "usuarios/UsuariosListing";
	public static final String NEW_USUARIO_FORM = "usuarios/createUsuarioForm";

    @Autowired
    UsuarioService usuarioService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("username");
	}
	
    @GetMapping
    public String listUsuarios(ModelMap model){
        model.addAttribute("usuarios", usuarioService.findAll());
        return USUARIOS_LISTING;
    }

    @GetMapping("/{username}/edit")
	public String editUsuario(@PathVariable("username") String username,ModelMap model) {
		Optional<Usuario> usuario=usuarioService.findByUsername(username);
		if(usuario.isPresent()) {
			model.addAttribute("usuario",usuario.get());
			return USUARIOS_FORM;
		}else {
			model.addAttribute("message","We cannot find the user you tried to edit!");
			return listUsuarios(model);
		}
	}

	@PostMapping("/{username}/edit")
	public String editUsuario(@PathVariable("username") String username, @Valid Usuario modifiedDisease, BindingResult binding, ModelMap model) {
		Optional<Usuario> usuario=usuarioService.findByUsername(username);
		if(binding.hasErrors()) {			
			return USUARIOS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedDisease, usuario.get(), "username");
			usuarioService.save(usuario.get());
			model.addAttribute("message","User updated succesfully!");
			return listUsuarios(model);
		}
	}

	@GetMapping("/{username}/delete")
	public String deleteUsuario(@PathVariable("username") String username,ModelMap model) {
		Optional<Usuario> usuario=usuarioService.findByUsername(username);
		if(usuario.isPresent()) {
			usuarioService.delete(usuario.get());
			model.addAttribute("message","The user was deleted successfully!");
			return listUsuarios(model);
		}else {
			model.addAttribute("message","We cannot find the user you tried to delete!");
			return listUsuarios(model);
		}
	}

	@GetMapping(value = "/usuarios/new")
	public String initCreationForm(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return NEW_USUARIO_FORM;
	}

	@PostMapping(value = "/usuarios/new")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			return NEW_USUARIO_FORM;
		}
		else {
			this.usuarioService.save(usuario);
			return "redirect:/";
		}
	}

}
