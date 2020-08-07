package br.com.eullen.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Estoque implements Serializable {
    private static final long serialVersionUID = 8153056722965273984L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0)
    private Long quantidade;

    @UpdateTimestamp
    private Date dataAtualizacao;

    @JsonIgnore
    @OneToOne(mappedBy = "estoque")
    private Produto produto;

    public Estoque() {

    }

    public Estoque(@Min(value = 0) Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estoque)) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id) &&
                Objects.equals(quantidade, estoque.quantidade) &&
                Objects.equals(dataAtualizacao, estoque.dataAtualizacao) &&
                Objects.equals(produto, estoque.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidade, dataAtualizacao, produto);
    }
}
