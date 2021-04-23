package com.seguradora.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	@Getter @Setter	
	private int id;

	@Column(name = "cidade")
	@Getter @Setter
	private String cidade;

	@Column(name = "cpf")
	@Getter @Setter
	private String cpf;

	@Column(name = "nome_completo")
	@Getter @Setter
	private String nomeCompleto;

	@Column(name = "uf")
	@Getter @Setter
	private String uf;

	public Cliente() {
	}

}