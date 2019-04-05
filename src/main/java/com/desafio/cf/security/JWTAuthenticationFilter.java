package com.desafio.cf.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.desafio.cf.domain.Usuario;
import com.desafio.cf.enums.Perfil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Usuario usuario = new ObjectMapper()
	                .readValue(request.getInputStream(), Usuario.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
		
		List<String> roles = ((UserSpringSecurity) authResult.getPrincipal())
				.getAuthorities()
				.stream()
				.map(x -> x.getAuthority()).collect(Collectors.toList());
		String perm = Perfil.toDesc(roles.stream().findFirst().orElse("ROLE_CLIENTE"));
		
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        response.addHeader("Permissions", perm);
        response.addHeader("access-control-expose-headers", "Permissions");
	}
	
}
