package br.com.eullen.ecommerce.service;

import br.com.eullen.ecommerce.entity.Produto;

public interface ProdutoService {

    /**
     * @param id
     * @return {@link Produto} encontrado
     */
    Produto recuperarProduto(Long id) throws RuntimeException;

    /**
     * @return Lista com todos os {@link Produto} cadastros no sistema
     */
    Iterable<Produto> recuperarTodosOsProdutos();

    /**
     * @param trechoNome
     * @return Lista com todos os {@link Produto} cadastros no sistema cujo nome contém a string informada
     */
    Iterable<Produto> recuperarProdutosPorNome(String trechoNome);

    /**
     * @param produto
     * @return {@link Produto} criado
     */
    Produto criarProduto(Produto produto);

    /**
     * @param produto
     * @return {@link Produto} atualizado
     */
    Produto salvarProduto(Produto produto);

    /**
     * @param idProduto
     * @param novoEstoque
     * @return {@link Produto} com estoque atualizado
     */
    Produto atualizarEstoque(Long idProduto, Long novoEstoque);

    /**
     * @param produtoId
     * @param quantidadeDesejada
     */
    void validarEstoqueProduto(Long produtoId, Long quantidadeDesejada);
}
