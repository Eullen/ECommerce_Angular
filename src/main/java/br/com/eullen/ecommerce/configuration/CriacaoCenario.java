package br.com.eullen.ecommerce.configuration;

import br.com.eullen.ecommerce.entity.*;
import br.com.eullen.ecommerce.repository.ProdutoCarrinhoRepository;
import br.com.eullen.ecommerce.service.CarrinhoService;
import br.com.eullen.ecommerce.service.ClienteService;
import br.com.eullen.ecommerce.service.HistoricoPedidoService;
import br.com.eullen.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class CriacaoCenario implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    HistoricoPedidoService historicoPedidoService;

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    ProdutoCarrinhoRepository produtoCarrinhoRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.criarProdutos();
        this.criarClientes();
    }

    private void criarProdutos() {
        Iterable<Produto> produtos = this.produtoService.recuperarTodosOsProdutos();
        if (!produtos.iterator().hasNext()) { //cria apenas se não existirem produtos no banco

            Produto p1 = new Produto();
            p1.setEstoque(new Estoque(2L));
            p1.setNome("Produto Com Descricao");
            p1.setDescricao("Descricao produto");
            p1.setValor(BigDecimal.valueOf(100.00));

            Produto p2 = new Produto();
            p2.setEstoque(new Estoque(2L));
            p2.setNome("Produto Sem Descricao");
            p2.setValor(BigDecimal.valueOf(250.99));

            Produto p3 = new Produto();
            p3.setEstoque(new Estoque(2L));
            p3.setNome("Produto Nome Diferente");
            p3.setValor(BigDecimal.valueOf(1999.99));

            Produto p4 = new Produto();
            p4.setEstoque(new Estoque(2L));
            p4.setNome("produto com descricao grande");
            p4.setDescricao("Descricao Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                    "when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
            p4.setValor(BigDecimal.valueOf(849.50));

            produtoService.salvarProduto(p1);
            produtoService.salvarProduto(p2);
            produtoService.salvarProduto(p3);
            produtoService.salvarProduto(p4);
        }
    }

    private void criarClientes() {
        //Todo: melhorar validacao
        Cliente cliente1 = new Cliente("Cliente Novo", "cn@email.com", "12345");
        Cliente cliente2 = new Cliente("Cliente Com Historico e Carrinho", "clienteVelho", "1010");
        clienteService.criarCliente(cliente1);
        clienteService.criarCliente(cliente2);
        this.criarHistoricoPedidoParaCliente(cliente2);
        this.criarCarrinhoParaCliente(cliente2);
    }

    private void criarHistoricoPedidoParaCliente(Cliente cliente){

        HistoricoPedido historicoPedido = new HistoricoPedido();
        historicoPedido.setCliente(cliente);

        ProdutoPedido pp1 = new ProdutoPedido();
        pp1.setNome("Produto Com Descricao");
        pp1.setDescricao("Descricao produto");
        pp1.setValor(BigDecimal.valueOf(100.00));
        pp1.setQuantidade(10L);
        pp1.setTotal(BigDecimal.valueOf(1000.00));
        pp1.setHistoricoPedido(historicoPedido);

        ProdutoPedido pp2 = new ProdutoPedido();
        pp2.setNome("Produto Que não existe mais");
        pp2.setDescricao("Produto descontinuado");
        pp2.setValor(BigDecimal.valueOf(1999.99));
        pp2.setQuantidade(2L);
        pp2.setTotal(pp2.getValor().multiply(BigDecimal.valueOf(4L)));
        pp2.setHistoricoPedido(historicoPedido);

        historicoPedido.setProdutosPedido(Arrays.asList(pp1, pp2));
        historicoPedidoService.salvarHistoricoPedido(historicoPedido);
    }

    private void criarCarrinhoParaCliente(Cliente cliente){

        Produto produto1 = this.produtoService.recuperarProduto(1L);
        Produto produto2 = this.produtoService.recuperarProduto(2L);

        Carrinho carrinho = cliente.getCarrinho();

        ProdutoCarrinho pc1 = new ProdutoCarrinho();
        pc1.setCarrinho(carrinho);
        pc1.setProduto(produto1);
        pc1.setQuantidade(1L);
        pc1.setId(new ProdutoCarrinhoKey(carrinho.getId(), produto1.getId()));

        ProdutoCarrinho pc2 = new ProdutoCarrinho();
        pc2.setCarrinho(carrinho);
        pc2.setProduto(produto2);
        pc2.setQuantidade(1L);
        pc2.setId(new ProdutoCarrinhoKey(carrinho.getId(), produto2.getId()));

        produtoCarrinhoRepository.saveAll(Arrays.asList(pc1, pc2));
    }
}
