package com.desafio.cf.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.cf.domain.Cliente;
import com.desafio.cf.domain.dto.ClienteDTO;
import com.desafio.cf.services.ClienteService;

@RestController
@RequestMapping(value="/api/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> listar() {
		List<Cliente> clientes = service.listar();
		List<ClienteDTO> dtoList = clientes.stream()
				.map(cliente -> new ClienteDTO(cliente))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(dtoList);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.buscar(id));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody Cliente cliente) {
		Cliente clientePersistido = service.inserir(cliente);
		URI uri = criarURLdeRetorno(clientePersistido);
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Cliente cliente, @PathVariable Integer id) {
		cliente.setId(id);
		service.alterar(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> alterar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="paginado", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> listarPaginado(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="linhas", defaultValue="24") Integer linhas, 
			@RequestParam(value="ordem", defaultValue="nome") String ordem, 
			@RequestParam(value="direcao", defaultValue="ASC") String direcao) {
		Page<Cliente> clientes = service.listarPaginado(pagina, linhas, ordem, direcao);
		Page<ClienteDTO> clientesDTO = clientes.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	private URI criarURLdeRetorno(Cliente cliente) {
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();
	}
}
