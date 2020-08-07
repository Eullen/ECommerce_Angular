package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.repository.ProdutoRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarrinhoServiceImplUnitTest {

    @Mock
    ProdutoRepository carrinhoRepository;

    @InjectMocks
    CarrinhoServiceImplUnitTest carrinhoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    

}
