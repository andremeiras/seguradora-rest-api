package com.seguradora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seguradora.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	// verificar se já existe o CPF
	@Query("Select count(c) from Cliente c where c.cpf = ?1") // ordem de parametros da query
	Integer buscarPorCPF(String cpf);
}
