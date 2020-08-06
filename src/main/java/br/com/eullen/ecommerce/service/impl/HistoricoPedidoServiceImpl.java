package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.*;
import br.com.eullen.ecommerce.exception.OperacaoInvalidaException;
import br.com.eullen.ecommerce.repository.HistoricoPedidoRepository;
import br.com.eullen.ecommerce.service.CarrinhoService;
import br.com.eullen.ecommerce.service.ClienteService;
import br.com.eullen.ecommerce.service.HistoricoPedidoService;
import br.com.eullen.ecommerce.service.ProdutoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class HistoricoPedidoServiceImpl implements HistoricoPedidoService {

    @Autowired
    private HistoricoPedidoRepository historicoPedidoRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    /**
     * @param carrinho
     * @return {@link HistoricoPedido} salvo
     */
    @Override
    public HistoricoPedido salvarHistoricoPedido(Carrinho carrinho, Long idCliente) {
        if (CollectionUtils.isEmpty(carrinho.getProdutosCarrinho())){
            throw new OperacaoInvalidaException("Não é possível salvar um pedido sem produtos");
        }
        this.carrinhoService.removerTodosOsProdutosDoCarrinho(carrinho.getId());
        return this.historicoPedidoRepository.save(this.montarHistoricoPedido(carrinho.getProdutosCarrinho(), idCliente));
    }

    private HistoricoPedido montarHistoricoPedido(Collection<ProdutoCarrinho> produtosCarrinho, Long idCliente){
        BigDecimal totalPedido = BigDecimal.valueOf(0);
        Collection<ProdutoPedido> produtosPedido = new ArrayList<>();
        HistoricoPedido historicoPedido = new HistoricoPedido();
        historicoPedido.setCliente(this.clienteService.recuperarCliente(idCliente));
        produtosCarrinho.forEach( produtoCarrinho -> {
            // Valida se tem estoque suficiente para realizar o pedido
            this.produtoService.validarEstoqueProduto(produtoCarrinho.getProduto().getId(), produtoCarrinho.getQuantidade());
            //Montando pedidoProduto a partir do produto do carrinho
            ProdutoPedido produtoPedido = new ProdutoPedido();
            BigDecimal totalProduto = produtoCarrinho.getProduto().getValor().multiply(BigDecimal.valueOf(produtoCarrinho.getQuantidade()));
            produtoPedido.setNome(produtoCarrinho.getProduto().getNome());
            produtoPedido.setDescricao(produtoCarrinho.getProduto().getDescricao());
            produtoPedido.setValor(produtoCarrinho.getProduto().getValor());
            produtoPedido.setQuantidade(produtoCarrinho.getQuantidade());
            produtoPedido.setTotal(totalProduto);
            produtoPedido.setHistoricoPedido(historicoPedido);
            produtosPedido.add(produtoPedido);
            totalPedido.add(totalProduto);
        });
        historicoPedido.setProdutosPedidos(produtosPedido);
        historicoPedido.setTotal(totalPedido);
        return historicoPedido;
    }

    @Override
    public Iterable<HistoricoPedido> recuperarHistoricoPedidosCliente(Long idCliente) {
        return this.historicoPedidoRepository.findAllByClienteId(idCliente);
    }

    /**
     * @param historicoPedido
     * @return {@link HistoricoPedido} salvo
     */
    @Override
    public HistoricoPedido salvarHistoricoPedido(HistoricoPedido historicoPedido) {
        return this.historicoPedidoRepository.save(historicoPedido);
    }


}
