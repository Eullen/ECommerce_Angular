package br.com.eullen.ecommerce.repository;

import br.com.eullen.ecommerce.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Cliente findByUsuarioIgnoreCase(String usuario);
}
