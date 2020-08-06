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
    - No terminal, acessar o repositório `src/main/js/front-end-ecommerce`
    - Rodar o comando: `ng serve --o`
    - Acessar a aplicação com: `http://localhost:4200/`

- BackEnd


### Cenário Existente

## Melhorias e Pendências
- Testes de Integração
   - ProdutoController
   - CarrinhoController
   - HistoricoPedidoController
   
- Testes unitários
    - ProdutoService
    - CarrinhoService
    - ClienteService
    - HistoricoPedidoService
    
- Tela de Carrinho
    - Adicionar funcionalidade de editar a quantidade de produtos no carrinho
    - Adicionar lógica para finalizar pedido
        - Salvar pedido
        - Redirecionar para historico

- Telas
    - Melhorar tratamento de mensagens de erro, sucesso 
    - Melhorar loading

- BackEnd
    - melhorar tratamento de exceções
    - Configurar swagger corretamente