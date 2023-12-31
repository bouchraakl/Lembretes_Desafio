# Projeto de Gestão de Pessoas e Lembretes

Este é um projeto de exemplo para demonstrar como criar um aplicativo usando Spring Boot para gerenciar informações de pessoas e lembretes. O aplicativo utiliza uma arquitetura de API REST para permitir a criação, leitura, atualização e exclusão de registros.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Banco de Dados PostgreSQL
- Maven 

## Configuração do Ambiente

1. Clone este repositório para a sua máquina local.
2. Certifique-se de que você tem o Java JDK instalado (versão recomendada: 11 ou superior).
3. Use o Maven para compilar e executar o projeto:

## Endpoints da API

### Lembrete

- **GET /api/lembrete/{id}**: Retorna um lembrete específico pelo seu ID.
- **GET /api/lembrete/all:** Retorna todos os lembretes.
- **GET /api/lembrete/all/{nome}:** Retorna todos os lembretes associados a uma pessoa pelo nome.
- **POST /api/lembrete:** Cria um novo lembrete. Envie um JSON com as informações do lembrete.
- **PUT /api/lembrete:** Edita um lembrete existente. Envie um JSON com as informações atualizadas do lembrete.
- **DELETE /api/lembrete:** Exclui um lembrete pelo ID.
  
### Pessoa
- **GET /api/pessoa/all:** Retorna todas as pessoas.
- **GET /api/pessoa/{id}:** Retorna uma pessoa específica pelo seu ID.
- **POST /api/pessoa:** Cadastra uma nova pessoa. Envie um JSON com as informações da pessoa.
- **PUT /api/pessoa:** Edita uma pessoa existente. Envie um JSON com as informações atualizadas da pessoa.
- **DELETE /api/pessoa:** Exclui uma pessoa pelo ID.
