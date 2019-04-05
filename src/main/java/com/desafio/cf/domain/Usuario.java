package com.desafio.cf.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.desafio.cf.enums.Perfil;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String senha;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIL")
	private Set<Integer> perfis = new HashSet<>();
	
	public Usuario() {
		addPerfil(Perfil.CLIENTE);
	}

	public Usuario(String login, String senha, Perfil perfil) {
		this.login = login;
		this.senha = senha;
		this.perfis.add(perfil.getCod());
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(perfil -> Perfil.toEnum(perfil)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.append(login)
				.append(senha)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return new EqualsBuilder()
				.append(id, other.getId())
				.append(login, other.getLogin())
				.append(senha, other.getSenha())
				.isEquals();
	}
	
}
