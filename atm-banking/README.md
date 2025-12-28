# ATM Banking System 🏦

Projeto Back-End robusto em Java que simula um sistema bancário, focado em regras de negócio, persistência de dados e arquitetura desacoplada.

## 🎯 Foco do Projeto: Back-End Independente
O núcleo desta aplicação foi desenvolvido para ser **totalmente independente da camada de visualização (View)**. 
* A lógica de negócio está isolada, permitindo que as Views atuais sejam facilmente substituídas por um front-end moderno (como Angular/React) ou integradas num ecossistema **Spring Boot**.
* O Back-End está pronto para ser exposto como uma API, bastando trocar as classes de interface.

## 🚀 Funcionalidades Principais
* **Gestão de Clientes:** Cadastro com validação de unicidade de CPF (não permite duplicados).
* **Validação de Documentos:** Integração com a biblioteca **Caelum Stella** para validação de CPFs reais.
* **Transações Financeiras:** Operações de Saque e Depósito com validações de saldo.
* **Histórico de Auditoria:** Cada movimentação gera um registo automático numa tabela de logs para rastreabilidade.

## 💾 Persistência de Dados
* **MySQL:** Base de dados relacional utilizada para armazenar clientes, saldos e históricos.
* **MySQL Connector/J:** Utilizado como biblioteca de repositório para a comunicação eficiente entre o Java e a base de dados.

## 🛠️ Tecnologias e Bibliotecas
* **Java 17**
* **Maven:** Gestão de dependências.
* **MySQL:** Banco de Dados.
* **Caelum Stella Core:** Validação de CPF.
* **Git:** Versionamento.

## 📖 Como rodar
1. Configure as credenciais do seu **MySQL** no ficheiro de configuração do projeto.
2. Certifique-se de ter o Maven instalado.
3. Compile o projeto: 
   ```bash
   mvn clean install
