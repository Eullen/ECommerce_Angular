package br.com.eullen.ecommerce.service;

import br.com.eullen.ecommerce.entity.Cliente;

public interface ClienteService {

    /**
     * @param novoCliente
     * @return {@link Cliente} criado
     */
    Cliente criarCliente(Cliente novoCliente);

    /**
     *
     * @param idCliente
     * @return {@link Cliente} criado
     */
    Cliente recuperarCliente(Long idCliente);
}
