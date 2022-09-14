package com.clubEcuestre.aplicacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clubEcuestre.aplicacion.model.TipoUsuario;


@Repository
public interface TipoUsuarioRepositorio extends CrudRepository<TipoUsuario, Long> {
	
	

}
