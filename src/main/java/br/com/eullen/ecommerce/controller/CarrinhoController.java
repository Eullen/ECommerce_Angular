package br.com.eullen.ecommerce.controller;

import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.ProdutoCarrinho;
import br.com.eullen.ecommerce.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrinhos")
@CrossOrigin
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping(value = "/{id}/produtos", produces = "application/json")
    public ResponseEntity<Carrinho> adicionarProdutoNoCarrinho(@PathVariable Long id, @RequestBody ProdutoCarrinho produtoCarrinho) {
        Carrinho carrinhoAtualizado = this.carrinhoService.adicionarProdutoCarrinho(id, produtoCarrinho);
        return new ResponseEntity<Carrinho>(carrinhoAtualizado, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/produtos", produces = "application/json")
    public ResponseEntity<Carrinho> atualizarProdutoNoCarrinho(@PathVariable Long id, @RequestBody ProdutoCarrinho produtoCarrinho) {
        Carrinho carrinhoAtualizado = this.carrinhoService.atualizarProdutoCarrinho(id, produtoCarrinho);
        return new ResponseEntity<Carrinho>(carrinhoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/produtos/{idProduto}", produces = "application/json")
    public ResponseEntity<Carrinho> removerProdutoDoCarrinho(@PathVariable Long id, @PathVariable Long idProduto) {
        Carrinho carrinhoAtualizado = this.carrinhoService.removerProdutoCarrinho(id, idProduto);
        return new ResponseEntity<Carrinho>(carrinhoAtualizado, HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}"}, produces = "application/json")
    public Carrinho recuperarCarrinho(@PathVariable Long id) {
        return this.carrinhoService.recuperarCarrinho(id);
    }

}
