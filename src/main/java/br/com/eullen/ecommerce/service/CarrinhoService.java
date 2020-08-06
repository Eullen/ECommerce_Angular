package br.com.eullen.ecommerce.service;

import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.ProdutoCarrinho;

import java.util.List;

public interface CarrinhoService {

    /**
     * @param idCarrinho
     * @return {@Link Carrinho} recuperado
     */
    Carrinho recuperarCarrinho(Long idCarrinho);

    /**
     * @param idCarrinho
     * @param produtoCarrinho
     * @return {@link Carrinho} atualizado
     */
    Carrinho adicionarProdutoCarrinho(Long idCarrinho, ProdutoCarrinho produtoCarrinho);

    /**
     * @param idCarrinho
     * @param produtoCarrinho
     * @return {@link Carrinho} atualizado
     */
    Carrinho atualizarProdutoCarrinho(Long idCarrinho, ProdutoCarrinho produtoCarrinho);

    /**
     * @param idCarrinho
     * @param idProduto
     * @return {@link Carrinho} atualizado
     */
    Carrinho removerProdutoCarrinho(Long idCarrinho, Long idProduto);

    /**
     * @param idCarrinho
     * @return {@link Carrinho} atualizado
     */
    Carrinho removerTodosOsProdutosDoCarrinho(Long idCarrinho);

    /**
     *
     * @param carrinho
     * @return {@link Carrinho} salvo
     */
    Carrinho salvarCarrinho(Carrinho carrinho);
}
