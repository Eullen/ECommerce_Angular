package br.com.eullen.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(indexes = @Index(columnList = "nome", name = "nome_produto"))
public class Produto implements Serializable, ProdutoBasico {

    private static final long serialVersionUID = 1603890816984961655L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @Column(scale = 2)
    @NotNull(message = "O valor do produto é obrigatório")
    @Min(value = 0, message = "O valor do produto deve ser maior que 0")
    private BigDecimal valor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estoque_id", referencedColumnName = "id")
    private Estoque estoque;

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private Collection<ProdutoCarrinho> itensCarrinho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        valor.setScale(2);
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(descricao, produto.descricao) &&
                Objects.equals(valor, produto.valor) &&
                Objects.equals(estoque, produto.estoque) &&
                Objects.equals(itensCarrinho, produto.itensCarrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, valor, estoque, itensCarrinho);
    }
}
