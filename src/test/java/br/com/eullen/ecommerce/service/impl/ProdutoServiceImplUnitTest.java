package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.Estoque;
import br.com.eullen.ecommerce.entity.Produto;
import br.com.eullen.ecommerce.exception.NotFoundException;
import br.com.eullen.ecommerce.exception.OperacaoInvalidaException;
import br.com.eullen.ecommerce.repository.ProdutoRepository;
import br.com.eullen.ecommerce.service.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceImplUnitTest {

    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
    ProdutoServiceImpl produtoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criarProdutoComEstoqueZeradoTest(){
        Produto produtoRequest = new Produto();
        produtoRequest.setNome("Teste");
        produtoRequest.setValor(BigDecimal.valueOf(1));
        when(produtoRepository.save(any(Produto.class))).then(AdditionalAnswers.returnsFirstArg());
        Produto produtoCriado = this.produtoService.criarProduto(produtoRequest);

        Assert.assertEquals(produtoCriado.getNome(), produtoRequest.getNome());
        Assert.assertEquals(produtoCriado.getValor(), produtoRequest.getValor());
        Assert.assertNotNull(produtoCriado.getEstoque());
        Assert.assertEquals(produtoCriado.getEstoque().getQuantidade().longValue(), 0L);

    }

    @Test(expected= OperacaoInvalidaException.class)
    public void validarEstoqueInsuficienteTest(){
        Produto produtoRequest = new Produto();
        produtoRequest.setEstoque(new Estoque(1L));
        when(this.produtoRepository.findById(any(Long.class))).thenReturn(Optional.of(produtoRequest));
        this.produtoService.validarEstoqueProduto(1L,2L);
    }

    @Test()
    public void validarEstoqueSuficienteTest(){
        Produto produtoRequest = new Produto();
        produtoRequest.setEstoque(new Estoque(1L));
        when(this.produtoRepository.findById(any(Long.class))).thenReturn(Optional.of(produtoRequest));
        this.produtoService.validarEstoqueProduto(1L,1L);
    }

    @Test()
    public void atualizarEstoqueTest(){
        Produto produtoRequest = new Produto();
        produtoRequest.setEstoque(new Estoque(1L));
        when(this.produtoRepository.findById(any(Long.class))).thenReturn(Optional.of(produtoRequest));
        when(produtoRepository.save(any(Produto.class))).then(AdditionalAnswers.returnsFirstArg());
        Produto produtoAtualizado = this.produtoService.atualizarEstoque(1l,10L);
        Assert.assertEquals(produtoAtualizado.getEstoque().getQuantidade(), produtoRequest.getEstoque().getQuantidade());
    }

    @Test(expected = NotFoundException.class)
    public void recuperarProdutoNaoExistenteTest(){
        when(this.produtoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        this.produtoService.recuperarProduto(1L);
    }
}
