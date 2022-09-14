package com.clubEcuestre.aplicacion.service;

import com.clubEcuestre.aplicacion.model.Usuario;

public interface UsuarioService {
	
	public Iterable<Usuario> getAllUsers();

	public Usuario crearUsuario(Usuario usuario) throws Exception;
	
	public Usuario getUsuarioById(Long id) throws Exception;
	
	public Usuario editarUsuario(Usuario usuario) throws Exception;

}
