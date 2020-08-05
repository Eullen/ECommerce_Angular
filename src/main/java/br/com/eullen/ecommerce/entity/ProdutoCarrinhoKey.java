package br.com.eullen.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutoCarrinhoKey implements Serializable {

    private static final long serialVersionUID = -8601561787948793966L;

    @Column(name = "carrinho_id")
    Long carrinhoId;

    @Column(name = "produto_id")
    Long produtoId;

    public ProdutoCarrinhoKey() {
    }

    public ProdutoCarrinhoKey(Long carrinhoId, Long produtoId) {
        this.carrinhoId = carrinhoId;
        this.produtoId = produtoId;
    }

    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoCarrinhoKey)) return false;
        ProdutoCarrinhoKey that = (ProdutoCarrinhoKey) o;
        return Objects.equals(carrinhoId, that.carrinhoId) &&
                Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrinhoId, produtoId);
    }
}
