package br.com.eullen.ecommerce.repository;

import br.com.eullen.ecommerce.entity.HistoricoPedido;
import org.springframework.data.repository.CrudRepository;

public interface HistoricoPedidoRepository extends CrudRepository<HistoricoPedido, Long> {
    Iterable<HistoricoPedido> findAllByClienteIdOrderByDataCadastroDesc(Long clienteId);
}
