package com.clubEcuestre.aplicacion.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubEcuestre.aplicacion.model.Usuario;
import com.clubEcuestre.aplicacion.repository.UsuarioRepositorio;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepositorio repositorio;

	@Override
	public Iterable<Usuario> getAllUsers() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}
	
	private boolean checkUsernameDisponible(Usuario usuario) throws Exception {
		
		Optional<Usuario> usuarioEncontrado = repositorio.findByUsername(usuario.getUsername());
		
		if(usuarioEncontrado.isPresent()) {
			
			throw new Exception("Username no disponible");
		}
		
		return true;
	}
	
	private boolean checkPasswordValido(Usuario usuario) throws Exception {
		
		if(usuario.getConfirmPassword() == null || usuario.getConfirmPassword().isEmpty()) {
			
			throw new Exception("Confirm Password es obligatorio");
		}
		
		if( !usuario.getPassword().equals(usuario.getConfirmPassword()) ) {
			
			throw new Exception("Password y Confirm Password no son iguales");
			
		}
		
		return true;
		
	}

	public Usuario crearUsuario(Usuario usuario) throws Exception {
		
		if(checkUsernameDisponible(usuario) && checkPasswordValido(usuario)) {
			
			usuario = repositorio.save(usuario);
			
		}
		return usuario;
	}

	@Override
	public Usuario getUsuarioById(Long id) throws Exception {
		
		return repositorio.findById(id).orElseThrow(() -> new Exception("El usuario para editar no existe"));
	}

	@Override
	public Usuario editarUsuario(Usuario fromUsuario) throws Exception {
		
		Usuario toUsuario = getUsuarioById(fromUsuario.getId());
		mapUsuario(fromUsuario, toUsuario); 
		return repositorio.save(toUsuario);
		
	}
	
	protected void mapUsuario(Usuario from, Usuario to) {
		
		to.setNombre(from.getNombre());
		to.setApellido(from.getApellido());
		to.setEmail(from.getEmail());
		to.setCelular(from.getCelular());
		to.setUsername(from.getUsername());
		to.setTipoUsuarios(from.getTipoUsuarios());
		
		
	}
	
	

}
