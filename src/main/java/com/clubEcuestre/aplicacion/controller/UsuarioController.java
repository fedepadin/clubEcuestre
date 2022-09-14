package com.clubEcuestre.aplicacion.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubEcuestre.aplicacion.model.Usuario;
import com.clubEcuestre.aplicacion.repository.TipoUsuarioRepositorio;
import com.clubEcuestre.aplicacion.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	TipoUsuarioRepositorio tipoUsuarioRepositorio;

	@Autowired
	UsuarioService usuarioService;

	@GetMapping({"/", "/login"})
	public String index() {
		return "index"; // Busca en la carpeta "templates" un archivo que se llame index.html
	}

	@GetMapping("/userForm")
	public String userForm(Model model) {
		model.addAttribute("userForm", new Usuario());
		model.addAttribute("userList", usuarioService.getAllUsers());
		model.addAttribute("tipoUsuarios", tipoUsuarioRepositorio.findAll());
		model.addAttribute("listTab", "active");
		return "user-form/user-view"; // Busca en la carpeta "templates -> user-form" un archivo que se llame user-view.html

	}
	
	// Gracias al method="post" en el user-form.html, viene y entra en el metodo @PostMapping y no en el @GetMapping.

	@PostMapping("/userForm")
	public String crearUsuarios(@Valid @ModelAttribute("userForm") Usuario usuario, BindingResult resultado, ModelMap model) {

		// @Valid valida el atributo, @BindingResult nos trae el resultado de la validacion.

		if (resultado.hasErrors()) {

			model.addAttribute("userForm", usuario); // El usuario que recibimos por parametro, se devuelve aca.
			model.addAttribute("formTab", "active"); // Le decimos que pestania quiero que me muestre.

		} else {
			
			try {
				usuarioService.crearUsuario(usuario);
				model.addAttribute("userForm", new Usuario()); 
				model.addAttribute("listTab", "active");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", usuario);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", usuarioService.getAllUsers());
				model.addAttribute("tipoUsuarios", tipoUsuarioRepositorio.findAll());
			}
			
		}

		model.addAttribute("userList", usuarioService.getAllUsers());
		model.addAttribute("tipoUsuarios", tipoUsuarioRepositorio.findAll());
		return "user-form/user-view";
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUsuarioForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		
		Usuario usuarioAEditar = usuarioService.getUsuarioById(id);
		
		model.addAttribute("userForm", usuarioAEditar);
		model.addAttribute("userList", usuarioService.getAllUsers());
		model.addAttribute("tipoUsuarios", tipoUsuarioRepositorio.findAll());
		model.addAttribute("formTab", "active"); 
		model.addAttribute("editMode", "active"); 
		
		return "user-form/user-view";
		
	}
	
	@PostMapping("/editUser")
	public String postEditUsuarioForm(@Valid @ModelAttribute("userForm") Usuario usuario, BindingResult resultado, ModelMap model) {

				if (resultado.hasErrors()) {

					model.addAttribute("userForm", usuario);
					model.addAttribute("formTab", "active");
					model.addAttribute("editMode", "active"); 
				} else {
					
					try {
						usuarioService.editarUsuario(usuario);
						model.addAttribute("userForm", new Usuario()); 
						model.addAttribute("listTab", "active");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						model.addAttribute("formErrorMessage", e.getMessage());
						model.addAttribute("userForm", usuario);
						model.addAttribute("formTab", "active");
						model.addAttribute("userList", usuarioService.getAllUsers());
						model.addAttribute("tipoUsuarios", tipoUsuarioRepositorio.findAll());
						model.addAttribute("editMode", "true");
					}
					
				}

				model.addAttribute("userList", usuarioService.getAllUsers());
				model.addAttribute("tipoUsuarios", tipoUsuarioRepositorio.findAll());
				return "user-form/user-view";
	}
	
	@GetMapping("/userForm/cancel")
	public String cancelarEdicionUsuario(ModelMap model) {
		return "redirect:/userForm";
	}

}
