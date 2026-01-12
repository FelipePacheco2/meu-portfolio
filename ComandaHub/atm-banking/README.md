# ATM Banking System 🏦

Projeto Back-End robusto em Java que simula um sistema bancário, focado em regras de negócio, persistência de dados e arquitetura desacoplada.

🎯 Foco do Projeto: Back-End Independente
O núcleo desta aplicação foi desenvolvido com foco exclusivo na lógica de negócio e persistência no Back-End.

* **Views Apenas para Testes** Toda a parte de visualização (menus e interação via console) foi construída estritamente para fins de teste e validação das funcionalidades. Ela não deve ser levada em consideração como o produto final de interface.
* **Arquitetura Desacoplada** A lógica está isolada de forma que o sistema possa ser facilmente migrado para uma API REST com Spring Boot ou conectado a front-ends modernos (como Angular/React), bastando descartar as classes de visualização atuais.
* **Pronto para Evolução** O Back-End funciona de forma autônoma, tratando as regras de saldo, validação de CPF e persistência em MySQL independentemente de como os dados são exibidos.

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
