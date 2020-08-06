package br.com.eullen.ecommerce.controller;

import br.com.eullen.ecommerce.entity.Estoque;
import br.com.eullen.ecommerce.entity.Produto;
import br.com.eullen.ecommerce.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Retorna lista de produtos")
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public Iterable<Produto> recuperarProdutos(@RequestParam Optional<String> trechoNome) {
        return trechoNome.isPresent() ? this.produtoService.recuperarProdutosPorNome(trechoNome.get()) : this.produtoService.recuperarTodosOsProdutos();
    }

    @ApiOperation(value = "Retorna produto com o id informado")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Produto> recuperarPorId(@PathVariable Long id) {
        Produto produto = this.produtoService.recuperarProduto(id);
        return new ResponseEntity<Produto>(produto, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto produtoCriado = this.produtoService.criarProduto(produto);
        return new ResponseEntity<Produto>(produtoCriado, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}/estoque", produces = "application/json")
    public ResponseEntity<Produto> atualizarEstoqueProduto(@RequestBody Estoque estoque, @PathVariable Long id) {
        Produto produtoAtualizado = this.produtoService.atualizarEstoque(id, estoque.getQuantidade());
        return new ResponseEntity<Produto>(produtoAtualizado, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto, @PathVariable Long id) {
        Produto produtoAtualizado = this.produtoService.salvarProduto(produto);
        return new ResponseEntity<Produto>(produtoAtualizado, HttpStatus.OK);
    }

}
