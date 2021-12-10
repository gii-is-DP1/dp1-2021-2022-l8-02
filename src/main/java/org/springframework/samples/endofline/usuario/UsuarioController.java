package org.springframework.samples.endofline.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.samples.endofline.statistics.Statistics;
import org.springframework.samples.endofline.statistics.StatisticsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public static final String LOBBY = "lobby";
	public static final String PROFILE = "profile";

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
	public String listUsuarios(ModelMap model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return USUARIOS_LISTING;
	}

	@GetMapping("/usuarios/{username}/edit")
	public String editUsuario(@PathVariable("username") String username, ModelMap model) {
		Optional<Usuario> usuario = usuarioService.findByUsername(username);
		if (usuario.isPresent()) {
			model.addAttribute("usuario", usuario.get());
			return USUARIOS_FORM;
		} else {
			model.addAttribute("message", "We cannot find the user you tried to edit!");
			return listUsuarios(model);
		}
	}

	@PostMapping("/usuarios/{username}/edit")
	public String editUsuario(@PathVariable("username") String username, @Valid Usuario modifiedUsuario,
			BindingResult binding, ModelMap model) {
		Optional<Usuario> usuario = usuarioService.findByUsername(username);
		if (binding.hasErrors()) {
			return USUARIOS_FORM;
		} else {
			BeanUtils.copyProperties(modifiedUsuario, usuario.get(), "username");
			usuarioService.save(usuario.get());
			model.addAttribute("message", "User updated succesfully!");
			return listUsuarios(model);
		}
	}

	@GetMapping("/usuarios/{username}/delete")
	public String deleteUsuario(@PathVariable("username") String username, ModelMap model) {
		Optional<Usuario> usuario = usuarioService.findByUsername(username);
		if (usuario.isPresent()) {
			usuarioService.delete(usuario.get());
			model.addAttribute("message", "The user was deleted successfully!");
			return listUsuarios(model);
		} else {
			model.addAttribute("message", "We cannot find the user you tried to delete!");
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
		List<String> list= new ArrayList<>();
		for(Usuario user :usuarioService.findAll()){
			String name= user.getUsername();
			list.add(name);
		}
		if (result.hasErrors()) {
			return USUARIOS_FORM;
		}
		else if(list.contains(usuario.getUsername())){
			return USUARIOS_FORM;
		}
		else{
			this.usuarioService.save(usuario);
			return "redirect:/usuarios";
			}
	}

	@GetMapping("/register")
	public String showRegisterForm(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return REGISTER_USER;
	}

	// Post para registrarse como nuevo usuario
	@PostMapping("/register")
	public String registerUser(@Valid Usuario usuario, BindingResult binding) {
		if (binding.hasErrors()) {
			return REGISTER_USER;
		} else if (usuarioService.getallUsernames().contains(usuario.getUsername())) {
			binding.rejectValue("username", "usernamex2", "Ya existe un usuario con este nombre");
			return REGISTER_USER;
		} else if (usuarioService.getallEmails().contains(usuario.getEmail())) {
			binding.rejectValue("email", "emailx2", "Ya existe un usuario con este email");
			return REGISTER_USER;
		} //else if (binding.getFieldValue("password") != binding.getFieldValue("passwordRepeat")){
		// 	binding.rejectValue("passwordRepeat", "passwordx2", "Las contraseñas deben coincidir");
		// 	return REGISTER_USER;
		// }
		 else {
			this.usuarioService.save(usuario);
			this.authoritiesSer.saveAuthorities(usuario.getUsername(), "jugador");
			Statistics s = new Statistics();
			s.setUsuario(usuario);
			s.setNumPlayers(0);
			s.setNumGames(0);
			s.setDuration(0);
			statisticsService.save(s);
			return "redirect:/lobby";
			/*Authentication authentication=;
			SecurityContextHolder.getContext().setAuthentication(authentication);*/
		}
	}

	@GetMapping("/inicio")
	public String PagInicial() {
		return INICIO;
	}

	private Usuario getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return usuarioService.findByUsername(user.getUsername()).orElseThrow(IllegalArgumentException::new);
    }
	
	@GetMapping("/lobby")
	public String PagLobby() {
		return LOBBY;
	}

	@GetMapping("/profile")
	public String profileLoggedUser(ModelMap model){
		model.addAttribute("usuario", getLoggedUser());
		return PROFILE;
	}
}
