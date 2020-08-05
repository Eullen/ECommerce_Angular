package br.com.eullen.ecommerce.repository;

import br.com.eullen.ecommerce.entity.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
