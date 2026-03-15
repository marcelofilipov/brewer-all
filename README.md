# 🍺 Brewer — Beer Sales Management

> Proof of Concept · Sistema de Gestão de Vendas de Cervejas

[![License: MIT](https://img.shields.io/github/license/marcelofilipov/brewer-all)](https://github.com/marcelofilipov/brewer-all/blob/master/LICENSE)
[![Repo Size](https://img.shields.io/github/repo-size/marcelofilipov/brewer-all)](https://github.com/marcelofilipov/brewer-all)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/marcelofilipov/brewer-all)](https://github.com/marcelofilipov/brewer-all/commits/master)

---

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

---

## Sobre o Projeto

O **Brewer** é uma aplicação web para gestão de vendas de cervejas, desenvolvida como Prova de Conceito (PoC). O objetivo principal é demonstrar o uso do ecossistema Spring em uma aplicação MVC completa, com autenticação, persistência de dados e interface responsiva.

---

## Tecnologias

**Back-end**
- Java
- Spring Framework (Spring MVC, Spring Boot)
- Spring Security
- Spring Data JPA / Hibernate
- Maven (gerenciamento de dependências e build)

**Front-end**
- Thymeleaf (template engine server-side)
- Bootstrap 4
- JavaScript / CSS

**Empacotamento**
- EAR (Enterprise Archive) — módulo `brewer-ear`

---

## Arquitetura

O projeto segue uma estrutura de **multi-módulo Maven**, separando responsabilidades entre os módulos:

```
brewer-all/
├── brewer-ear/     # Empacotamento EAR da aplicação
├── brewer-web/     # Módulo web (MVC, templates, estáticos)
└── pom.xml         # POM pai (parent)
```

A camada web utiliza **Spring MVC com Thymeleaf** para renderização server-side, com autenticação gerenciada pelo **Spring Security**. A persistência é feita via **Spring Data JPA**, abstraindo o acesso ao banco de dados.

---

## Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- **Java** 8 ou superior
- **Maven** 3.6+
- Servidor de banco de dados compatível (ex: MySQL, PostgreSQL)
- (Opcional) Servidor de aplicação compatível com EAR (ex: WildFly, JBoss)

---

## Instalação e Execução

**1. Clone o repositório**

```bash
git clone https://github.com/marcelofilipov/brewer-all.git
cd brewer-all
```

**2. Configure o banco de dados**

Edite o arquivo de propriedades em `brewer-web/src/main/resources/application.properties` (ou equivalente) com as credenciais do seu banco:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/brewer
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

**3. Build do projeto**

```bash
mvn clean install
```

**4. Execute a aplicação**

```bash
cd brewer-web
mvn spring-boot:run
```

Acesse: [http://localhost:8080](http://localhost:8080)

---

## Estrutura do Projeto

```
brewer-all/
├── brewer-ear/
│   └── pom.xml
├── brewer-web/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/          # Classes Java (controllers, services, repositories)
│   │   │   ├── resources/     # Configurações (application.properties, SQL)
│   │   │   └── webapp/        # Templates Thymeleaf, CSS, JS
│   │   └── test/
│   └── pom.xml
├── .editorconfig
├── .gitignore
├── LICENSE
├── pom.xml
└── README.md
```

---

## Contribuindo

Contribuições são bem-vindas. Para mudanças significativas, abra uma *issue* primeiro para discutir o que deseja alterar.

1. Faça um **fork** do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/minha-feature`)
3. Faça commit das alterações (`git commit -m 'feat: adiciona minha feature'`)
4. Faça push para a branch (`git push origin feature/minha-feature`)
5. Abra um **Pull Request**

> Por favor, certifique-se de atualizar ou adicionar testes quando aplicável.

---

## Licença

Distribuído sob a licença **MIT**. Veja [`LICENSE`](https://github.com/marcelofilipov/brewer-all/blob/master/LICENSE) para mais informações.

---

<p align="center">Desenvolvido por <a href="https://github.com/marcelofilipov">marcelofilipov</a></p>
