package br.com.eullen.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Carrinho implements Serializable {

    private static final long serialVersionUID = -1448620819051509951L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "carrinho", fetch = FetchType.EAGER)
    private List<ProdutoCarrinho> produtosCarrinho;

    @CreationTimestamp
    private Date dataCriacao;

    @JsonIgnore
    @OneToOne(mappedBy = "carrinho")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProdutoCarrinho> getProdutosCarrinho() {
        return this.produtosCarrinho;
    }

    public void setProdutosCarrinho(List<ProdutoCarrinho> produtosCarrinho) {
        this.produtosCarrinho = produtosCarrinho;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
