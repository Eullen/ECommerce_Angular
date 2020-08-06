package br.com.eullen.ecommerce.dto;

import java.io.Serializable;

public class ClienteDto implements Serializable {

    private static final long serialVersionUID = -2885357378013380223L;

    private Long id;

    private String nome;

    private String usuario;

    private Long idCarrinho;

    public ClienteDto(Long id, String nome, String usuario, Long idCarrinho) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.idCarrinho = idCarrinho;
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

    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }
}
