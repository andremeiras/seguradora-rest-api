package com.seguradora.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@NamedQuery(name="Apolice.findAll", query="SELECT a FROM Apolice a")
public class Apolice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	@Column(name = "id")
	private int id;

	@Column(name = "data_fim_vigencia")
	@Getter @Setter
	private String dataFimVigencia;

	@Column(name = "data_inicio_vigencia")
	@Getter @Setter
	private String dataInicioVigencia;

	@Column(name = "numero_apolice")
	@Getter @Setter
	private String numeroApolice;

	@Column(name = "placa_veiculo")
	@Getter @Setter
	private String placaVeiculo;

	@Column(name = "valor_apolice")
	@Getter @Setter
	private String valorApolice;

	public Apolice() {
	}
	
}