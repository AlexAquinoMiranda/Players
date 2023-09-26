package com.players.app.models.service;

import java.util.List;




import com.players.app.models.entity.Equipo;
import com.players.app.models.entity.Jugador;
import com.players.app.models.entity.Torneo;


public interface IJugadorService {

	public List<Jugador> findAllJugador();

	public List<Equipo> findAllEquipo();

	public void saveJugador(Jugador jugador);

	public Jugador findOne(Long id);

	public void deleteJugador(Long id);

	public void saveEquipo(Equipo equipo);

	public Jugador findJugadorById(Long id);

	public Equipo findEquipoById(Long id);

	public void deleteEquipo(Long id);

	public void saveTorneo(Torneo torneo);

}
