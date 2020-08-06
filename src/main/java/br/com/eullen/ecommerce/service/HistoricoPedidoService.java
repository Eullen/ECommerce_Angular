package br.com.eullen.ecommerce.service;

import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.HistoricoPedido;

public interface HistoricoPedidoService {

    /**
     *
     * @param carrinho
     * @return {@link HistoricoPedido} salvo
     */
    HistoricoPedido salvarHistoricoPedido(Carrinho carrinho, Long idCliente);

    /**
     *
     * @param historicoPedido
     * @return {@link HistoricoPedido} salvo
     */
    HistoricoPedido salvarHistoricoPedido(HistoricoPedido historicoPedido);

    /**
     *
     * @param idCliente
     * @return lista com os {@link HistoricoPedido} do cliente
     */
    Iterable<HistoricoPedido> recuperarHistoricoPedidosCliente(Long idCliente);
}
