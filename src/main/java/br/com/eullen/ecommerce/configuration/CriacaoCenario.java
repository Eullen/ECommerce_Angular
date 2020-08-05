package br.com.eullen.ecommerce.configuration;

import br.com.eullen.ecommerce.entity.Cliente;
import br.com.eullen.ecommerce.entity.Estoque;
import br.com.eullen.ecommerce.entity.Produto;
import br.com.eullen.ecommerce.service.ClienteService;
import br.com.eullen.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CriacaoCenario implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ClienteService clienteService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.criarProdutos();
        this.criarClientes();
    }

    private void criarProdutos(){
        Iterable<Produto> produtos = this.produtoService.recuperarTodosOsProdutos();
        if (!produtos.iterator().hasNext()){ //cria apenas se não existirem produtos no banco

            Produto p1 = new Produto();
            p1.setEstoque(new Estoque(2L));
            p1.setNome("Produto 1");
            p1.setDescricao("Descricao 1");
            p1.setValor(BigDecimal.valueOf(100.00));

            Produto p2 = new Produto();
            p2.setEstoque(new Estoque(2L));
            p2.setNome("Produto 2");
            p2.setValor(BigDecimal.valueOf(250.99));

            Produto p3 = new Produto();
            p3.setEstoque(new Estoque(2L));
            p3.setNome("Produto 3");
            p3.setValor(BigDecimal.valueOf(1999.99));

            Produto p4 = new Produto();
            p4.setEstoque(new Estoque(2L));
            p4.setNome("Produto 4");
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

    private void criarClientes(){
        //Todo: melhorar validacao
        Cliente cliente1 = new Cliente("clienteDummy","cliente@email.com","12345");
        Cliente cliente2 = new Cliente("clienteFake","fake","1010");

        clienteService.criarCliente(cliente1);
        clienteService.criarCliente(cliente2);
    }
}
