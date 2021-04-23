package com.seguradora.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seguradora.model.Apolice;

public interface ApoliceRepository extends JpaRepository<Apolice, Integer>{
	
	@Query("SELECT a.numeroApolice from Apolice a")
	ArrayList<String> listarApolices();

	@Query("SELECT a from Apolice a where a.numeroApolice = ?1")
	Apolice buscarApolicePorNumero(String numeroApolice);

}
