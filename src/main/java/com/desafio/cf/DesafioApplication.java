package com.desafio.cf;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.desafio.cf.domain.Cliente;
import com.desafio.cf.domain.Endereco;
import com.desafio.cf.domain.Usuario;
import com.desafio.cf.enums.Perfil;
import com.desafio.cf.repositories.ClienteRepository;
import com.desafio.cf.repositories.UsuarioRepository;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Cliente jorge = new Cliente("jorge", "12345678900");
		jorge.setTelefones(new HashSet<>(Arrays.asList("6136242222","61938383929","619292837387")));
		jorge.setEmails(new HashSet<>(Arrays.asList("jorge@gmail.com")));
		jorge.setEndereco(new Endereco("72222333", "rua benedito", "santa maria", "brasilia", "df", "casa 02", jorge));
		Cliente matheus = new Cliente("Matheus Torres", "98765432100");
		matheus.setTelefones(new HashSet<>(Arrays.asList("6163453636","613826283","619393872")));
		matheus.setEmails(new HashSet<>(Arrays.asList("matheus@yahoo.com.br")));
		matheus.setEndereco(new Endereco("30120111", "rua maranhão", "savassi", "belo horizonte", "mg", "apto 1045", matheus));
		Cliente carlos = new Cliente("Carlos Andrade", "98798798798");
		carlos.setTelefones(new HashSet<>(Arrays.asList("61323421","6193834134","611234123")));
		carlos.setEmails(new HashSet<>(Arrays.asList("carlos@gmail.com")));
		carlos.setEndereco(new Endereco("29122222", "rua antonio marcos", "consolacao", "sao paulo", "sp", "apto 906", carlos));
		Cliente juliana = new Cliente("Juliana Maria", "65498732165");
		juliana.setTelefones(new HashSet<>(Arrays.asList("61361234123","613241239","6134114")));
		juliana.setEmails(new HashSet<>(Arrays.asList("jmaria@hotmail.com")));
		juliana.setEndereco(new Endereco("19129139", "rua augusta", "jardins", "sao paulo", "sp", "casa 03", juliana));
		Cliente ana = new Cliente("Ana", "98798778900");
		ana.setTelefones(new HashSet<>(Arrays.asList("612341243","6132411239","61134123")));
		ana.setEmails(new HashSet<>(Arrays.asList("ana@locaweb.com")));
		ana.setEndereco(new Endereco("23929999", "rua maria do carmo", "jardins", "sao paulo", "sp", "casa 15", ana));
		
		clienteRepository.saveAll(Arrays.asList(jorge,matheus,carlos, juliana, ana));
		
		Usuario admin = new Usuario("admin", bcrypt.encode("123456"), Perfil.ADMIN);
		
		Usuario comum = new Usuario("comum", bcrypt.encode("123456"));
		
		usuarioRepository.saveAll(Arrays.asList(admin, comum));
		
	}

}
