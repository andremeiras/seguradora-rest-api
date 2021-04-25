# API REST - Seguradora de veículos

## Teste técnico - Dev Java Júnior ou Pleno
Crie um projeto Web ou API REST para uma seguradora de veículos.

### Tecnologias que devem ser usadas:
- Spring Boot (Maven ou Gradle);
- MongoDB ou um banco de dados relacional;
- Bootstrap, Angular ou outro framework para front-end (caso escolha projeto Web).
### CRUD de clientes:
- Dados: Nome Completo, CPF, Cidade e UF;
- Todos os dados são obrigatórios;
- CPF deve ser válido e deve ser único na base.
### CRUD de apólices:
- Dados: Número da apólice, Início de vigência, Fim de vigência, Placa do veículo e Valor da apólice;
- Todos os dados são obrigatórios;
- O número da apólice deve ser gerado aleatoriamente e ser único.
### Consultar uma apólice por número:
- Tela ou endpoint separado dos CRUDs;
- Informar se a apólice venceu ou não;
- Informar quantos dias para vencer, ou há quantos dias venceu;
- Informar placa do veículo e valor da apólice.
### Finalização:
- Disponibilizar imagem Docker para execução da aplicação ou WAR;
- Publicar no GitHub (privado) e comparlhar com Rafael Hayashi (@rafaelhayashi).