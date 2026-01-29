<div align="center">

# 🏥 HMMS
## Hospital Maintenance Management System

### Gestão Inteligente de Manutenção Hospitalar

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white" alt="Flyway" />
  <img src="https://img.shields.io/badge/MapStruct-V1.5-brightgreen?style=for-the-badge" alt="MapStruct" />
</p>

</div>

---

## 📄 Sobre o Projeto

O **HMMS** é uma API RESTful robusta desenvolvida para solucionar o desafio de rastreabilidade na manutenção de equipamentos médicos. O sistema controla todo o ciclo de vida, desde o cadastro da **Location** (sala/ala) e **Heritage** (patrimônio) até a execução complexa de **Ordens de Serviço (OS)**.

Diferente de um CRUD simples, este projeto implementa regras de negócio estritas para garantir que a manutenção só seja acionada quando necessário.

---

## 🧠 Destaque: A Lógica da Ordem de Serviço

O diferencial deste back-end é o algoritmo de validação ("Gatekeeper") na criação de Ordens de Serviço. O fluxo impede a criação de OS duplicadas ou desnecessárias.

> **Como funciona o algoritmo:**

1.  **Entrada de Dados:** O usuário envia uma lista de `IDs` de ocorrências.
2.  **Processamento:**
    * 🔴 **Bloqueio:** Se todas as ocorrências já estiverem vinculadas a uma OS ou finalizadas, o sistema **rejeita** a criação.
    * 🟢 **Filtragem:** Se houver uma mistura de ocorrências (algumas válidas, outras inválidas), o sistema **filtra** e cria a OS apenas com as pendentes.
3.  **Feedback:** A API retorna um JSON detalhando quais ocorrências entraram na OS e quais foram ignoradas.

---

## 🛠 Tech Stack

| Categoria | Tecnologias Utilizadas |
| :--- | :--- |
| **Core** | Java 17, Spring Boot 3 |
| **Banco de Dados** | MySQL 8, Flyway (Migrations) |
| **ORM & Data** | Spring Data JPA, Hibernate |
| **Mapeamento** | MapStruct (DTO Pattern) |
| **Utilitários** | Lombok, Maven |

---

## 🚀 Como Executar

### Pré-requisitos
* Java 17+
* MySQL Server rodando

### 1. Clone o repositório
```bash
git clone [https://github.com/SEU-USUARIO/SEU-REPO.git](https://github.com/SEU-USUARIO/SEU-REPO.git)
