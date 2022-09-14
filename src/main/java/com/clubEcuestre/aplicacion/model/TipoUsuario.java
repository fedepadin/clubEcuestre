package com.clubEcuestre.aplicacion.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class TipoUsuario implements Serializable {

	private static final long serialVersionUID = -8229774514735980645L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native") // Utiliza el auto-incremento de MySql
	@GenericGenerator(name="native", strategy="native")
	private Long id; 
	
	@Column
	private String tipoUsuario;
	
	@Column
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipoUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoUsuario other = (TipoUsuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(tipoUsuario, other.tipoUsuario);
	}

	@Override
	public String toString() {
		return "TipoUsuario [id=" + id + ", tipoUsuario=" + tipoUsuario + "]";
	}
	


	

}
