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
     * @param produtosCarrinho
     * @return {@link Carrinho} atualizado
     */
    Carrinho atualizarProdutosDoCarrinho(Long idCarrinho, List<ProdutoCarrinho> produtosCarrinho);
}
