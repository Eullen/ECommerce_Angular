package br.com.eullen.ecommerce.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public  Cliente(){

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

    public Cliente(Cliente cliente){
        super();
        this.carrinho = cliente.getCarrinho();
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
}
