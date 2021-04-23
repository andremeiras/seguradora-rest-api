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

import com.seguradora.model.Apolice;
import com.seguradora.repository.ApoliceRepository;
import com.seguradora.utils.Utils;
import com.seguradora.vo.ApoliceVO;

@RestController
@RequestMapping("/apolice")
public class ApoliceController {

	@Autowired
	ApoliceRepository apoliceRepository;

	Apolice apo = new Apolice();

	@GetMapping("/lista")
	public ResponseEntity<List<Apolice>> listarTodasApolices() {
		try {
			List<Apolice> apo = new ArrayList<Apolice>();

			apoliceRepository.findAll().forEach(apo::add);

			if (apo.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(apo, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Apolice> buscarApolicePorId(@PathVariable("id") Integer id) {
		Optional<Apolice> apolice = apoliceRepository.findById(id);

		if (apolice.isPresent()) {
			return new ResponseEntity<>(apolice.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/numeroApolice/{id}")
	public ResponseEntity<ApoliceVO> buscarApolicePorNumero(@PathVariable("id") String numeroApolice) {
		Apolice apolice = apoliceRepository.buscarApolicePorNumero(numeroApolice);

		ApoliceVO apoVO = new ApoliceVO(apolice);
		if (apolice != null) {
			return new ResponseEntity<>(apoVO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/novo")
	public ResponseEntity<Apolice> criarApolice(@RequestBody Apolice apolice) {

		ArrayList<String> listaApolices = apoliceRepository.listarApolices();

		Apolice apoNovo = new Apolice();

		apoNovo.setNumeroApolice(Utils.randomNovo(listaApolices));
		apoNovo.setPlacaVeiculo(apolice.getPlacaVeiculo());
		apoNovo.setValorApolice(apolice.getValorApolice());
		apoNovo.setDataInicioVigencia(apolice.getDataInicioVigencia());
		apoNovo.setDataFimVigencia(apolice.getDataFimVigencia());

		// TODO - fazer validação se já existe a apólice

		try {
			Apolice _apolice = apoliceRepository.save(apoNovo);
			return new ResponseEntity<>(_apolice, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Apolice> atualizarApolice(@PathVariable("id") Integer id, @RequestBody Apolice apolice) {
		Optional<Apolice> apoliceBanco = apoliceRepository.findById(id);

		if (apoliceBanco.isPresent()) {
			Apolice _apolice = apoliceBanco.get();

			if (!" ".equals(apolice.getNumeroApolice())) {
				_apolice.setNumeroApolice(apolice.getNumeroApolice());
			}
			_apolice.setPlacaVeiculo(apolice.getPlacaVeiculo());
			_apolice.setValorApolice(apolice.getValorApolice());
			_apolice.setDataInicioVigencia(apolice.getDataInicioVigencia());
			_apolice.setDataFimVigencia(apolice.getDataFimVigencia());

			// TODO - fazer validação para verificar se já existe

			return new ResponseEntity<>(apoliceRepository.save(_apolice), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletarApolice(@PathVariable("id") int id) {
		try {
			apoliceRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
