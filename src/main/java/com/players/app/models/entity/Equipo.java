package com.players.app.models.entity;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.*;

import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "equipo")
public class Equipo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @OneToMany(mappedBy = "equipo")
	// private List<Jugador> jugadores;

	
	private String nombre;
	private String descripcion;
	
	private String escudo;
	private Integer puntos;
	// @ManyToOne
	// @JoinColumn(name = "torneo_id")
	// private Torneo torneo;

	public Equipo() {
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEscudo() {
		return escudo;
	}

	public void setEscudo(String escudo) {
		this.escudo = escudo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
