package com.players.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.players.app.models.dao.IJugadorDao;

import com.players.app.models.entity.Equipo;
import com.players.app.models.entity.Jugador;
import com.players.app.models.entity.Torneo;

@Service
public class JugadorServiceImpl implements IJugadorService {
	
	
	@Autowired(required=true)
	private IJugadorDao jugadorDao;

	@Override
	@Transactional(readOnly = true)
	public Jugador findOne(Long id) {

		return jugadorDao.findById(id).orElse(null);
		// return null;
	}

	@Override
	@Transactional
	public void deleteJugador(Long id) {
		 jugadorDao.deleteById(id);

	}

	@Override
	@Transactional
	public void saveEquipo(Equipo equipo) {
		// TODO Auto-generated method stub
		// this.equipoDao.save(equipo);
	}

	@Override
	@Transactional(readOnly = true)
	public Jugador findJugadorById(Long id) {
		// TODO Auto-generated method stub
		 return jugadorDao.findById(id).orElse(null);
		//return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Equipo findEquipoById(Long id) {

		// return ((CrudRepository<Equipo, Long>)
		// this.equipoDao).findById(id).orElse(null);
		return null;
	}

	@Override
	@Transactional
	public void deleteEquipo(Long id) {

		// this.equipoDao.deleteById(id);

	}

	@Override
	@Transactional
	public void saveJugador(Jugador jugador) {
		
		if (this.jugadorDao != null) {
			
			 System.out.print("No es nulo");
		    this.jugadorDao.save(jugador);
		} else {
		   System.err.print("ERROROROORORORORO");
		   
		   
		}
		
	}

	@Override
	@Transactional
	public void saveTorneo(Torneo torneo) {
		// this.torneoDao.save(torneo);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findAllJugador() {

		 return (List<Jugador>)jugadorDao.findAll();
		//return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findAllEquipo() {
		// return (List<Equipo>) this.equipoDao.findAll();
		return null;
	}

}
