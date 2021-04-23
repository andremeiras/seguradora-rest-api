DROP DATABASE if EXISTS teste;
create database teste;

use teste;

DROP TABLE IF EXISTS cliente;
CREATE TABLE cliente (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nomeCompleto varchar(200) not null,
	cpf varchar(200) not null,
	cidade varchar(200) not null,
	uf varchar(4) not null
);

DROP TABLE IF EXISTS apolice;
Create table apolice(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    numeroApolice varchar(200) not null,
	dataInicioVigencia varchar(200) not null,
	dataFimVigencia varchar(200) not null,
	placaVeiculo varchar(200) not null,
	valorApolice varchar(200) not null
);