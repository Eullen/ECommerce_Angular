package br.com.eullen.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class ProdutoPedido implements Serializable, ProdutoBasico {

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

    @Min(value = 1, message = "A quantidade deve ser maior ou igual a 1.")
    private Long quantidade;

    @Column(scale = 2)
    @Min(value = 0, message = "A quantidade deve ser maior ou igual a 0.")
    private BigDecimal total;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="historicoPedido_id", nullable=false)
    private HistoricoPedido historicoPedido;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public BigDecimal getValor() {
        return this.valor;
    }

    @Override
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public HistoricoPedido getHistoricoPedido() {
        return historicoPedido;
    }

    public void setHistoricoPedido(HistoricoPedido historicoPedido) {
        this.historicoPedido = historicoPedido;
    }

}
