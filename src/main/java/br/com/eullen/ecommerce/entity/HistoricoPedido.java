package br.com.eullen.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class HistoricoPedido implements Serializable {

    private static final long serialVersionUID = -6181216088301998072L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date dataCadastro;

    @OneToMany(mappedBy="historicoPedido", cascade = CascadeType.ALL)
    private Collection<ProdutoPedido> produtosPedidos;

    @Column(scale = 2)
    @NotNull(message = "O total do pedido é obrigatório")
    private BigDecimal total;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Collection<ProdutoPedido> getProdutosPedidos() {
        return produtosPedidos;
    }

    public void setProdutosPedidos(Collection<ProdutoPedido> produtosPedidos) {
        this.produtosPedidos = produtosPedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoricoPedido)) return false;
        HistoricoPedido that = (HistoricoPedido) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dataCadastro, that.dataCadastro) &&
                Objects.equals(produtosPedidos, that.produtosPedidos) &&
                Objects.equals(total, that.total) &&
                Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCadastro, produtosPedidos, total, cliente);
    }
}
