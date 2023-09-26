package com.players.app.models.entity;

import java.io.Serializable;



import jakarta.persistence.*;


@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String Apellido;
	
	private String dni;
	
	private String foto;
	
	private Integer dorsal;

	//@ManyToOne
	//@JoinColumn(name = "equipo_id")
	//private Equipo equipo;

	public Jugador() {

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
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", Apellido=" + Apellido + ", dni=" + dni + ", foto=" + foto
				+ ", dorsal=" + dorsal + "]";
	}

}
