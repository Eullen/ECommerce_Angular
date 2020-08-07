package br.com.eullen.ecommerce.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = -3861481512402491689L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do cliente é obrigatório")
    private String nome;

    @NotNull(message = "O usuário do cliente é obrigatório")
    private String usuario;

    @NotNull(message = "A senha do cliente é obrigatório")
    private String senha;

    @CreationTimestamp
    private Date dataCadastro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_id", referencedColumnName = "id")
    private Carrinho carrinho;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Collection<HistoricoPedido> historicoPedidos;

    public Cliente() {

    }

    public Cliente(@NotNull(message = "O nome do cliente é obrigatório") String nome, @NotNull(message = "O usuário do cliente é obrigatório") String usuario, @NotNull(message = "A senha do cliente é obrigatório") String senha) {
        super();
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Cliente(@NotNull(message = "O nome do cliente é obrigatório") String nome, @NotNull(message = "O usuário do cliente é obrigatório") String usuario) {
        super();
        this.nome = nome;
        this.usuario = usuario;
    }

    public Cliente(Cliente cliente) {
        super();
        this.carrinho = cliente.getCarrinho();
        this.historicoPedidos = cliente.getHistoricoPedidos();
        this.id = cliente.getId();
        this.dataCadastro = cliente.getDataCadastro();
        this.senha = cliente.getSenha();
        this.nome = cliente.getNome();
        this.usuario = cliente.getUsuario();
    }

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Collection<HistoricoPedido> getHistoricoPedidos() {
        return historicoPedidos;
    }

    public void setHistoricoPedidos(Collection<HistoricoPedido> historicoPedidos) {
        this.historicoPedidos = historicoPedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(usuario, cliente.usuario) &&
                Objects.equals(senha, cliente.senha) &&
                Objects.equals(dataCadastro, cliente.dataCadastro) &&
                Objects.equals(carrinho, cliente.carrinho) &&
                Objects.equals(historicoPedidos, cliente.historicoPedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario, senha, dataCadastro, carrinho, historicoPedidos);
    }
}
