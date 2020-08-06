package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.ProdutoCarrinho;
import br.com.eullen.ecommerce.entity.ProdutoCarrinhoKey;
import br.com.eullen.ecommerce.exception.NotFoundException;
import br.com.eullen.ecommerce.repository.CarrinhoRepository;
import br.com.eullen.ecommerce.repository.ProdutoCarrinhoRepository;
import br.com.eullen.ecommerce.service.CarrinhoService;
import br.com.eullen.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    ProdutoCarrinhoRepository produtoCarrinhoRepository;

    @Autowired
    private ProdutoService produtoService;

    /**
     * @param idCarrinho
     * @return {@Link Carrinho} recuperado
     */
    @Override
    public Carrinho recuperarCarrinho(Long idCarrinho) {
        return this.carrinhoRepository
                .findById(idCarrinho)
                .orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));
    }

    /**
     * @param idCarrinho
     * @param produtoCarrinho
     * @return {@link Carrinho} atualizado
     */
    @Override
    public Carrinho adicionarProdutoCarrinho(Long idCarrinho, final ProdutoCarrinho produtoCarrinho) {
        Carrinho carrinho = this.recuperarCarrinho(idCarrinho);
        this.produtoCarrinhoRepository
                .findByIdCarrinhoIdAndIdProdutoId(idCarrinho, produtoCarrinho.getProduto().getId())
                .ifPresent(produtoCarrinhoExistente -> {
                    produtoCarrinho.setQuantidade(produtoCarrinhoExistente.getQuantidade() + produtoCarrinho.getQuantidade());
                });

        this.produtoService.validarEstoqueProduto(produtoCarrinho.getProduto().getId(), produtoCarrinho.getQuantidade());
        ProdutoCarrinho produtoCarrinhoAtualizado = this.salvarProdutoCarrinho(produtoCarrinho, idCarrinho);
        carrinho.getProdutosCarrinho().add(produtoCarrinhoAtualizado);
        return this.carrinhoRepository.save(carrinho);
    }

    /**
     * @param idCarrinho
     * @param produtoCarrinho
     * @return {@link Carrinho} atualizado
     */
    @Override
    public Carrinho atualizarProdutoCarrinho(Long idCarrinho, ProdutoCarrinho produtoCarrinho) {
        if (!this.carrinhoRepository.existsById(idCarrinho)) {
            throw new NotFoundException("Carrinho não encontrado");
        }
        //populando produtoe e validando estoque
        this.produtoService.validarEstoqueProduto(produtoCarrinho.getProduto().getId(), produtoCarrinho.getQuantidade());
        this.salvarProdutoCarrinho(produtoCarrinho, idCarrinho);
        return this.recuperarCarrinho(idCarrinho);
    }

    /**
     * @param idCarrinho
     * @param idProduto
     * @return {@link Carrinho} atualizado
     */
    @Override
    public Carrinho removerProdutoCarrinho(Long idCarrinho, Long idProduto) {
        ProdutoCarrinho produtoCarrinho =
                this.produtoCarrinhoRepository
                        .findByIdCarrinhoIdAndIdProdutoId(idCarrinho, idProduto)
                        .orElseThrow(() -> new NotFoundException("Produto não encontrado nesse carrinho"));
        Carrinho carrinho = this.recuperarCarrinho(idCarrinho);
        carrinho.getProdutosCarrinho().remove(produtoCarrinho);
        this.produtoCarrinhoRepository.delete(produtoCarrinho);
        return this.carrinhoRepository.save(carrinho);
    }

    /**
     * @param idCarrinho
     * @return {@link Carrinho} atualizado
     */
    @Override
    public Carrinho removerTodosOsProdutosDoCarrinho(Long idCarrinho) {
        Carrinho carrinho = this.recuperarCarrinho(idCarrinho);
        carrinho.getProdutosCarrinho().clear();
        this.produtoCarrinhoRepository.deleteByCarrinhoId(idCarrinho);
        return this.carrinhoRepository.save(carrinho);
    }

    /**
     * @param carrinho
     * @return {@link Carrinho} salvo
     */
    @Override
    public Carrinho salvarCarrinho(Carrinho carrinho) {
        return this.carrinhoRepository.save(carrinho);
    }

    public ProdutoCarrinho salvarProdutoCarrinho(ProdutoCarrinho produtoCarrinho, Long idCarrinho) {
        produtoCarrinho.setId(new ProdutoCarrinhoKey(idCarrinho, produtoCarrinho.getProduto().getId()));
        return this.produtoCarrinhoRepository.save(produtoCarrinho);
    }
}
