package com.players.app.models.entity;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name = "torneo")
public class Torneo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@OneToMany(mappedBy = "torneo")
	//private List<Equipo> equipos;
	
	private Equipo equipoGanador;

	public Torneo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Equipo getEquipoGanador() {
		return equipoGanador;
	}

	public void setEquipoGanador(Equipo equipoGanador) {
		this.equipoGanador = equipoGanador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
