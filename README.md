# TGID BackEnd Developer Project

## Descrição

Este projeto é um sistema de gerenciamento de transações financeiras entre clientes e empresas. Ele permite realizar depósitos e saques, gerenciar saldos, validar CPF e CNPJ, e enviar notificações e callbacks.

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação utilizada.
- **Spring Boot 3.3.3**: Framework para criação de aplicações Java.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **H2 Database**: Banco de dados em memória utilizado para testes e por praticidade no desenvolvimento, o ideal seria utilizar PostgreSQL ou MySQL e Flyway.
- **Spring Data JPA**: Abstração de persistência de dados.
- **Springdoc OpenAPI**: Documentação da API.

## Estrutura do Projeto

- **controllers**: Contém os controladores REST que expõem as APIs.
- **models**: Contém as entidades JPA.
- **repositories**: Contém as interfaces de repositório para acesso aos dados.
- **service**: Contém a lógica de negócios.
- **utils**: Contém classes utilitárias, como validação de CPF e CNPJ.
- **exception**: Contém exceções customizadas.

## Funcionalidades

- **Validação de CPF e CNPJ**: Implementada na classe `Validator`.
- **Gestão de Saldo**: Implementada nos serviços `ClienteService` e `EmpresaService`.
- **Transações e Callbacks**: Implementada no serviço `TransacaoService`.
- **Tratamento de Erros**: Implementado no `GlobalExceptionHandler`.
- **Documentação da API**: Utilizando `springdoc-openapi`.

## Endpoints Principais

### Clientes

- **POST /clientes**: Cadastrar um novo cliente.
- **PUT /clientes/{id}**: Atualizar um cliente existente.
- **DELETE /clientes/{id}**: Excluir um cliente existente.
- **GET /clientes/{id}**: Buscar um cliente pelo ID.
- **GET /clientes**: Listar todos os clientes.

### Empresas

- **POST /empresas**: Cadastrar uma nova empresa.
- **PUT /empresas/{id}**: Atualizar uma empresa existente.
- **DELETE /empresas/{id}**: Excluir uma empresa existente.
- **GET /empresas/{id}**: Buscar uma empresa pelo ID.
- **GET /empresas**: Listar todas as empresas.

### Transações

- **POST /transacoes/deposito**: Realizar um depósito.
- **POST /transacoes/saque**: Realizar um saque.


### Acesse a documentação da API:
    - Acesse `http://localhost:8080/swagger-ui.html` para visualizar a documentação da API.
