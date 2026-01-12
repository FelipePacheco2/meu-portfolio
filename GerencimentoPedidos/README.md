# 🍽️ Sistema de Gerenciamento de Restaurante (API REST)

## Descrição do Projeto
Esta API foi projetada para otimizar o fluxo de trabalho em um restaurante, conectando o atendimento, a cozinha e o fechamento de contas em tempo real. O sistema gerencia desde a abertura da comanda até a finalização do pagamento, garantindo a integridade dos dados através de uma estrutura SQL.

## Arquitetura e Endpoints

O sistema é dividido em três módulos principais baseados nos perfis de usuário:

### Garçom (Atendimento)
Gerencia a entrada de pedidos e consultas de mesa.
* **`GET` /pedidos**: Busca pedidos com filtros por *Número da Mesa* e *Status*.
* **`POST` /pedidos**: Abre uma nova comanda ou adiciona novos itens a uma já existente.

### Cozinha (Produção)
Módulo focado na eficiência da preparação dos pratos.
* **`GET` /itens-pedido**: Lista itens com status `"Preparando"` filtrados pelo tipo `"Refeição"`.
* **`PUT` /itens-pedido/{id}**: Atualiza o status do pedido para "Pronto" assim que o preparo é finalizado.

### Caixa (Fechamento)
Responsável pela conciliação financeira.
* **`GET` /pagamentos**: Consulta pedidos com status `"Pronto"` para processar o fechamento por mesa.
* **`PUT` /pagamentos/{id}**: Atualiza o status do item e encerra o fluxo financeiro do cliente.

---

## Estrutura de Dados (Database)
A persistência é feita em um banco SQL com três tabelas principais:
1.  **BD:Pedidos**: Cabeçalho e controle de mesas.
2.  **BD:ItemPedidos**: Detalhamento dos produtos e status de preparo.
3.  **BD:Pagamentos**: Registro de transações e finalizações.
4.  **BD:itens**: Guarda os itens que podem ser pedidos

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java / Python (Back-end)
* **Banco de Dados:** SQL (PostgreSQL/MySQL)
* **Documentação:** Swagger UI
* **Modelagem:** Diagrama de Sequência e Fluxo de Dados
