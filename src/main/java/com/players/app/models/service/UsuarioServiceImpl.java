package com.players.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.players.app.models.dao.IUsuarioDao;
import com.players.app.models.entity.Usuario;
@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao userDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUser() {
		// TODO Auto-generated method stub
		return (List<Usuario>) userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}

	@Override
	@Transactional
	public void saveUser(Usuario usuario) {
		userDao.save(usuario);
		// TODO Auto-generated method stub

	}

}
