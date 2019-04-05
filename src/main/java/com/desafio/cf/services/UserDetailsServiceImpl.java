package com.desafio.cf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desafio.cf.domain.Usuario;
import com.desafio.cf.repositories.UsuarioRepository;
import com.desafio.cf.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = repository.findByLogin(login);
		if(usuario==null) throw new UsernameNotFoundException(login);
		return new UserSpringSecurity(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getPerfis());
	}

}
