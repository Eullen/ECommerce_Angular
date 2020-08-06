package br.com.eullen.ecommerce.entity;

import java.math.BigDecimal;

public interface ProdutoBasico {

    Long getId();

    void setId(Long id);

    String getNome();

    void setNome(String nome);

    BigDecimal getValor();

    void setValor(BigDecimal valor);

    String getDescricao();

    void setDescricao(String descricao);
}
