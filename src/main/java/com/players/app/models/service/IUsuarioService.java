package com.players.app.models.service;

import java.util.List;

import com.players.app.models.entity.Equipo;
import com.players.app.models.entity.Jugador;
import com.players.app.models.entity.Torneo;
import com.players.app.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAllUser();

	public Usuario findOne(Long id);

	public Usuario findUsuarioById(Long id);

	public void deleteUser(Long id);

	public void saveUser(Usuario usuario);
}
