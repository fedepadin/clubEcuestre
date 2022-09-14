package com.clubEcuestre.aplicacion.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class Usuario implements Serializable {
	

	private static final long serialVersionUID = 756928344932837652L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native") // Utiliza el auto-incremento de MySql.
	@GenericGenerator(name="native", strategy="native")
	private Long id;
	
	@Column
	@NotBlank
	@Size(min=2, max=12, message="Numero de caracteres excedido")
	private String nombre;
	
	@Column
	@NotBlank
	private String apellido;
	
	@Column
	@Email
	@NotBlank
	private String email;
	
	@Column
	@NotNull
	private int celular;
	
	@Column
	@NotBlank
	private String username;
	
	@Column
	@NotBlank
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roles_usuario",
			joinColumns = @JoinColumn(name = "id_usuario"),
						inverseJoinColumns = @JoinColumn(name = "id_tipo_usuario"))
	
	private Set<TipoUsuario> tipoUsuarios; // Con el set obligo a que no se repita ningun valor. Siempre va a tener un valor unico.

	public Usuario() {
	}
	
	public Usuario(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<TipoUsuario> getTipoUsuarios() {
		return tipoUsuarios;
	}

	public void setTipoUsuarios(Set<TipoUsuario> tipoUsuarios) {
		this.tipoUsuarios = tipoUsuarios;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", celular="
				+ celular + ", username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", tipoUsuarios=" + tipoUsuarios + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, celular, confirmPassword, email, id, nombre, password, tipoUsuarios, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && celular == other.celular
				&& Objects.equals(confirmPassword, other.confirmPassword) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(tipoUsuarios, other.tipoUsuarios)
				&& Objects.equals(username, other.username);
	}
	
	
	
	
	
	

}
