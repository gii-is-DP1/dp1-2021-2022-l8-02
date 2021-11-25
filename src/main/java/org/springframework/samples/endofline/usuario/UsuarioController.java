package org.springframework.samples.endofline.usuario;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.endofline.statistics.Statistics;
import org.springframework.samples.endofline.statistics.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    public static final String USUARIOS_FORM = "usuarios/CUDUsuariosForm";
	public static final String USUARIOS_LISTING = "usuarios/UsuariosListing";
	public static final String NEW_USUARIO_FORM = "usuarios/createUsuarioForm";
	public static final String REGISTER_USER = "usuarios/registerUser";
	public static final String LOGIN_USER = "login";
	public static final String INICIO = "inicio";
	public static final String ERROR = "login-error";

    @Autowired
    UsuarioService usuarioService;

	@Autowired
	AuthoritiesService authoritiesSer;

	@Autowired
    StatisticsService statisticsService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
    @GetMapping("/usuarios")
    public String listUsuarios(ModelMap model){
        model.addAttribute("usuarios", usuarioService.findAll());
        return USUARIOS_LISTING;
    }

    @GetMapping("/usuarios/{username}/edit")
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

	@PostMapping("/usuarios/{username}/edit")
	public String editUsuario(@PathVariable("username") String username, @Valid Usuario modifiedUsuario, BindingResult binding, ModelMap model) {
		Optional<Usuario> usuario=usuarioService.findByUsername(username);
		if(binding.hasErrors()) {			
			return USUARIOS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedUsuario, usuario.get(), "username");
			usuarioService.save(usuario.get());
			model.addAttribute("message","User updated succesfully!");
			return listUsuarios(model);
		}
	}

	@GetMapping("/usuarios/{username}/delete")
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
		return USUARIOS_FORM;
	}

	@PostMapping(value = "/usuarios/new")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			return USUARIOS_FORM;
		}
		else {
			this.usuarioService.save(usuario);
			return "redirect:/usuarios";
		}
	}

	@GetMapping("/register")
    public String showRegisterForm(ModelMap model){
        model.addAttribute("usuario", new Usuario());
		return REGISTER_USER;
    }

	//Post para registrarse como nuevo usuario
	@PostMapping("/register")
	public String registerUser(@Valid Usuario usuario, BindingResult binding) {
		if(binding.hasErrors()) {			
			return REGISTER_USER;
		}else {
			this.usuarioService.save(usuario);
			this.authoritiesSer.saveAuthorities(usuario.getUsername(),"jugador");
			Statistics s = new Statistics();
			s.setUsuario(usuario);
			s.setNumPlayers(0);
			s.setNumGames(0);
			s.setDuration(0);
			statisticsService.save(s);
			return "redirect:/login";
		}
	}
 
	// @GetMapping("/login")
	// public String logUser(ModelMap model){
	// 	model.addAttribute("usuario", new Usuario());
	// 	return LOGIN_USER;
	// }

	@GetMapping("/inicio")
	public String PagInicial(){
		return INICIO;
	}

	// @GetMapping("/login-error")
	// public String logError(ModelMap model){
	// 	model.addAttribute("usuario", new Usuario());
	// 	return ERROR;
	// }
}
