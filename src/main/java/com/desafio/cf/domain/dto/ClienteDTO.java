package com.desafio.cf.domain.dto;

import java.io.Serializable;

import com.desafio.cf.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public ClienteDTO() {
	}

	public ClienteDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
