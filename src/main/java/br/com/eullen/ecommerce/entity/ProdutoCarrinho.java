package br.com.eullen.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ProdutoCarrinho implements Serializable {

    private static final long serialVersionUID = -6171750838623027274L;

    @JsonIgnore
    @EmbeddedId
    private ProdutoCarrinhoKey id;

    @JsonIgnore
    @ManyToOne()
    @MapsId("carrinho_id")
    @JoinColumn(name="carrinho_id")
    private Carrinho carrinho;

    @ManyToOne()
    @MapsId("produto_id")
    @JoinColumn(name="produto_id")
    private Produto produto;

    @NotNull( message = "É obrigatório informar a quantidade para o item do carrinho")
    @Min(value=1, message = "Um item do carrinho deve ter a quantidade maior ou igual a 1")
    private Long quantidade;

    public ProdutoCarrinhoKey getId() {
        return id;
    }

    public void setId(ProdutoCarrinhoKey id) {
        this.id = id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoCarrinho)) return false;
        ProdutoCarrinho that = (ProdutoCarrinho) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(carrinho, that.carrinho) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carrinho, produto);
    }
}
