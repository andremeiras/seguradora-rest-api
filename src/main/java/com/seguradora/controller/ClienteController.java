package com.seguradora.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguradora.model.Cliente;
import com.seguradora.repository.ClienteRepository;
import com.seguradora.utils.Utils;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteRepository cliRepository;

	Cliente cli = new Cliente();

	@GetMapping("/lista")
	public ResponseEntity<List<Cliente>> listarClientes() {
		try {
			List<Cliente> cli2 = new ArrayList<Cliente>();

			cliRepository.findAll().forEach(cli2::add);

			if (cli2.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cli2, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = cliRepository.findById(id); // Optional neste caso é devido ao uso do isPresent() e evitar NullPointerException - Java 8

		if (cliente.isPresent()) {
			return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/novo")
	public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) {
		
		int existeCadastro = 0;
		
		existeCadastro = cliRepository.buscarPorCPF(cliente.getCpf().replaceAll("[^0-9]", "")); // o mesmo que o TRIM

		if (existeCadastro != 0) {
			// ResponseEntity<?> permite passar uma mensagem no retorno ("CPF já cadastrado").
			return new ResponseEntity<>("CPF já cadastrado!", HttpStatus.PRECONDITION_REQUIRED); 
		} else {
			cli.setNomeCompleto(cliente.getNomeCompleto());
			cli.setCidade(cliente.getCidade());
			cli.setCpf(cliente.getCpf().replaceAll("[^0-9]", ""));
			cli.setUf(cliente.getUf());

			try {
				if (Utils.isValidCPF(cliente.getCpf().replaceAll("[^0-9]", ""))) {
					Cliente _cliente = cliRepository.save(cli);
					return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("CPF não é válido!", HttpStatus.PRECONDITION_REQUIRED);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
		Optional<Cliente> clienteBanco = cliRepository.findById(id);

		if (clienteBanco.isPresent()) {
			Cliente _cliente = clienteBanco.get();
			_cliente.setNomeCompleto(cliente.getNomeCompleto());
			_cliente.setCidade(cliente.getCidade());
			_cliente.setCpf(cliente.getCpf());
			_cliente.setUf(cliente.getUf());
			
			return new ResponseEntity<>(cliRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletarCliente(@PathVariable("id") int id) {
		try {
			cliRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
