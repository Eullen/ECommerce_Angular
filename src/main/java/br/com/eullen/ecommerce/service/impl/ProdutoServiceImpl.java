package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.Estoque;
import br.com.eullen.ecommerce.entity.Produto;
import br.com.eullen.ecommerce.exception.NotFoundException;
import br.com.eullen.ecommerce.exception.OperacaoInvalidaException;
import br.com.eullen.ecommerce.repository.ProdutoRepository;
import br.com.eullen.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * @param id
     * @return {@link Produto} encontrado
     */
    @Override
    public Produto recuperarProduto(Long id) throws RuntimeException {
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    /**
     * @return Lista com todos os {@link Produto} cadastros no sistema
     */
    @Override
    public Iterable<Produto> recuperarTodosOsProdutos() {
        return produtoRepository.findAll();
    }

    /**
     * @param trechoNome
     * @return Lista com todos os {@link Produto} cadastros no sistema cujo nome contém a string informada
     */
    @Override
    public Iterable<Produto> recuperarProdutosPorNome(String trechoNome) {
        return produtoRepository.findByNomeContainingIgnoreCase(trechoNome);
    }

    /**
     * @param produto
     * @return {@link Produto} criado
     */
    @Override
    public Produto criarProduto(Produto produto) {
        if (produto.getEstoque() == null) {
            Estoque estoqueZerado = new Estoque();
            estoqueZerado.setQuantidade(0L);
            estoqueZerado.setDataAtualizacao(new Date());
            produto.setEstoque(estoqueZerado);
        }
        return this.produtoRepository.save(produto);
    }

    /**
     * @param produto
     * @return {@link Produto} atualizado
     */
    @Override
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    /**
     * @param idProduto
     * @param novoEstoque
     * @return {@link Produto} com estoque atualizado
     */
    @Override
    public Produto atualizarEstoque(Long idProduto, Long novoEstoque) {
        Produto produtoAtualizado = this.recuperarProduto(idProduto);
        produtoAtualizado.getEstoque().setQuantidade(novoEstoque);
        return this.produtoRepository.save(produtoAtualizado);
    }

    /**
     * @param produtoId
     * @param quantidadeDesejada
     */
    @Override
    public void validarEstoqueProduto(Long produtoId, Long quantidadeDesejada) {
        Produto produto = this.recuperarProduto(produtoId);
        if (!(produto.getEstoque().getQuantidade() >= quantidadeDesejada)) {
            throw new OperacaoInvalidaException("Estoque insuficiente para realizar essa operação.");
        }
    }
}
