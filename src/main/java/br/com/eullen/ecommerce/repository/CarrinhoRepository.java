package br.com.eullen.ecommerce.repository;

import br.com.eullen.ecommerce.entity.Carrinho;
import org.springframework.data.repository.CrudRepository;

public interface CarrinhoRepository extends CrudRepository<Carrinho, Long> {
}
