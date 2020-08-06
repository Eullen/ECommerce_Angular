package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.HistoricoPedido;
import br.com.eullen.ecommerce.entity.ProdutoPedido;
import br.com.eullen.ecommerce.repository.HistoricoPedidoRepository;
import br.com.eullen.ecommerce.service.CarrinhoService;
import br.com.eullen.ecommerce.service.HistoricoPedidoService;
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

    /**
     * @param carrinho
     * @return {@link HistoricoPedido} salvo
     */
    @Override
    public HistoricoPedido salvarHistoricoPedido(Carrinho carrinho) {
        Collection<ProdutoPedido> produtosPedido = new ArrayList<>();
        carrinho.getProdutosCarrinho().forEach( produtoCarrinho -> {
            //Montando pedidoProduto a partir do produto do carrinho
            ProdutoPedido produtoPedido = new ProdutoPedido();
            produtoPedido.setNome(produtoCarrinho.getProduto().getNome());
            produtoPedido.setDescricao(produtoCarrinho.getProduto().getDescricao());
            produtoPedido.setValor(produtoCarrinho.getProduto().getValor());
            produtoPedido.setQuantidade(produtoCarrinho.getQuantidade());
            produtoPedido.setTotal(produtoCarrinho.getProduto().getValor().multiply(BigDecimal.valueOf(produtoCarrinho.getQuantidade())));
            produtosPedido.add(produtoPedido);
        });
        HistoricoPedido historicoPedido = new HistoricoPedido();
        historicoPedido.setProdutosPedido(produtosPedido);
        this.carrinhoService.removerTodosOsProdutosDoCarrinho(carrinho.getId());
        return this.historicoPedidoRepository.save(historicoPedido);
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
