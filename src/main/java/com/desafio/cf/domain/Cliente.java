package com.desafio.cf.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="não pode estar vazio")
	@Length(min=3, max=100, message="Campo nome deve ser maior que 3 e menor que 100 caracteres")
	private String nome;
	@NotBlank(message="não pode estar vazio")
	private String cpf;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Endereco endereco;
	@NotEmpty(message="Pelo menos um telefone deve ser informado")
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	@NotEmpty(message="Pelo menos um email deve ser informado")
	@ElementCollection
	@CollectionTable(name="EMAIL")
	private Set<String> emails = new HashSet<>();;
	
	public Cliente() {
	}

	public Cliente(Integer id, String nome, String cpf, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	public Cliente(String nome, String cpf, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<String> getTelefones() {
		return Collections.unmodifiableSet(telefones);
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public Set<String> getEmails() {
		return Collections.unmodifiableSet(emails);
	}
	
	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.id)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return new EqualsBuilder()
				.append(this.id, other.getCpf())
				.isEquals();
	}
}
