# Projeto E-Commerce com Angular e Spring Boot

O objetivo desse projeto é construir uma aplicação de e-commerce para atender os requisitos descritos a seguir, 
utilizando Java, Angular, SpringBoot, SpringData e OAuth2.
Requisitos: 
- Login do usuário não deve ser case sensitive.
- Senha do usuário deve ser mantida criptografada.
- O carrinho de produtos deve ser mantido no banco de dados.
- Todo pedido do cliente deve ser realizado com sucesso.
- O sistema deve conter ao menos um cliente registrado na base para testes.
- O sistema deve conter ao menos 4 produtos registrados com valores (100, 250.99, 1999.99 e 849.50).
- O sistema deve conter o estoque de 2 itens de cada produto.
- Não deve ser possível o cliente adicionar o produto caso não tenha estoque.
- O cliente pode ver o produto mesmo que não tenha estoque dele.
- Cliente pode ver a lista de produtos sem estar logado.
- Cliente só pode interagir com o carrinho estando logado.

## Subindo os projetos
- Front End
    - Necessário Angular, Node e Npm
    - No terminal, acessar o repositório `src/main/js/front-end-ecommerce`
    - Rodar o comando: `npm install`
    - Rodar o comando: `ng serve --o`
    - Acessar a aplicação com: `http://localhost:4200/`

- BackEnd
    - Necessário Java 8 ou superior e Maven instalados
    - No terminal, acessar a pasta do projeto
    - Rodar o comando: `./mvnw spring-boot:run`
    - O projeto sobe em: `http:localhost:8081/ecommerce`
        - Autenticacao: `http:localhost:8081/oauth/token`
        - Apis: `http:localhost:8081/ecommerce/api/{endpoint}`

- A aplicação cria cenários ao subir:
    - Clientes:
        - Cliente Novo
            - Usuario: clientenovo@email.com 
            - Senha: cn12345
            - Esse cliente nao possui carrinho ou pedidos
        - Cliente Com Historico e Carrinho
            - Usuario: clientevelho
            - Senha: 1010
            - Esse cliente possui produtos no carrinho e histórico de pedidos
    - Produtos:
        - 4 produtos com 2 itens no estoque cada

### Cenário Existente

## Melhorias e Pendências
- Testes unitários
    - CarrinhoService
    - ClienteService
    - HistoricoPedidoService

- Testes de Integração
   - ProdutoController
   - CarrinhoController
   - HistoricoPedidoController

- Telas
    - Melhorar tratamento de mensagens de erro, sucesso 
    - Melhorar loading
    
- FrontEnd
    - Modularizar app para se adequar ao padrões
    - Melhorar os models
    - Melhorar layout telas
    - Centralizar montagem das requests