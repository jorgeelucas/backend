package com.desafio.cf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cf.domain.Usuario;
import com.desafio.cf.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario buscarPorLogin(String login) {
		return repository.findByLogin(login);
	}

}
