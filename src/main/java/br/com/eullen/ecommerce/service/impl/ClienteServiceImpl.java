package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.Cliente;
import br.com.eullen.ecommerce.repository.ClienteRepository;
import br.com.eullen.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param novoCliente
     * @return {@link Cliente} criado
     */
    @Override
    public Cliente criarCliente(Cliente novoCliente) {
        Carrinho carrinho = new Carrinho();
        carrinho.setProdutosCarrinho(Collections.emptyList());
        novoCliente.setCarrinho(carrinho);
        novoCliente.setSenha(passwordEncoder.encode(novoCliente.getSenha()));
        novoCliente.setUsuario(novoCliente.getUsuario().toLowerCase());
        return clienteRepository.save(novoCliente);
    }

}
