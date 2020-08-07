package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.Estoque;
import br.com.eullen.ecommerce.entity.Produto;
import br.com.eullen.ecommerce.entity.ProdutoCarrinho;
import br.com.eullen.ecommerce.exception.NotFoundException;
import br.com.eullen.ecommerce.repository.CarrinhoRepository;
import br.com.eullen.ecommerce.repository.ProdutoCarrinhoRepository;
import br.com.eullen.ecommerce.repository.ProdutoRepository;
import br.com.eullen.ecommerce.service.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarrinhoServiceImplUnitTest {

    @Mock
    CarrinhoRepository carrinhoRepository;

    @Mock
    ProdutoCarrinhoRepository produtoCarrinhoRepository;

    @Mock
    ProdutoServiceImpl produtoService;

    @InjectMocks
    CarrinhoServiceImpl carrinhoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NotFoundException.class)
    public void recuperarProdutoNaoExistenteTest(){
        when(this.carrinhoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        this.carrinhoService.recuperarCarrinho(1L);
    }

    @Test
    public void removerTodosOsProdutosTest(){
        Carrinho carrinho = new Carrinho();
        List<ProdutoCarrinho> produtoCarrinhoList = Stream.of(new ProdutoCarrinho(), new ProdutoCarrinho())
                .collect(Collectors.toList());;
        carrinho.setProdutosCarrinho(produtoCarrinhoList);
        Assert.assertEquals(carrinho.getProdutosCarrinho().size(), 2);

        when(this.carrinhoRepository.findById(any(Long.class))).thenReturn(Optional.of(carrinho));
        when(this.produtoCarrinhoRepository.deleteByCarrinhoId(any())).thenReturn(Optional.empty());
        this.carrinhoService.removerTodosOsProdutosDoCarrinho(1L);

        Assert.assertTrue(carrinho.getProdutosCarrinho().isEmpty());
    }

}
