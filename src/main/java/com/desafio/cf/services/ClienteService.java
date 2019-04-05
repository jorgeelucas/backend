package com.desafio.cf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.desafio.cf.domain.Cliente;
import com.desafio.cf.repositories.ClienteRepository;
import com.desafio.cf.services.exceptions.InvalidFieldException;
import com.desafio.cf.services.exceptions.ObjectNotFoundException;
import com.desafio.cf.util.StringUtil;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente de id " + id + " não encontrado."));
	}

	public List<Cliente> listar() {
		return repository.findAll();
	}

	public Cliente inserir(Cliente cliente) {
		cliente.setId(null);
		validarEmails(cliente);
		validarTelefones(cliente);
		return repository.save(cliente);
	}
	
	public void alterar(Cliente cliente) {
		buscar(cliente.getId());
		repository.save(cliente);
	}

	public void deletar(Integer id) {
		Cliente cliente = buscar(id);
		repository.delete(cliente);
	}

	public Page<Cliente> listarPaginado(Integer pagina, Integer linhas, String ordem, String direcao) {
		PageRequest pr = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordem);
		return repository.findAll(pr);
	}
	
	private void validarTelefones(Cliente cliente) {
		if(StringUtil.isAlgumTelefoneInvalido(cliente.getTelefones())) 
			throw new InvalidFieldException("Telefone invalido");
	}

	private void validarEmails(Cliente cliente) {
		if(StringUtil.isAlgumEmailInvalido(cliente.getEmails())) 
			throw new InvalidFieldException("Email invalido");
	}

}
