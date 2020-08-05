package br.com.eullen.ecommerce.repository;

import br.com.eullen.ecommerce.entity.ProdutoCarrinho;
import br.com.eullen.ecommerce.entity.ProdutoCarrinhoKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProdutoCarrinhoRepository extends CrudRepository<ProdutoCarrinho, ProdutoCarrinhoKey> {
    Optional<ProdutoCarrinho> findByIdCarrinhoIdAndIdProdutoId(Long carrinhoId, Long produtoId);
}

