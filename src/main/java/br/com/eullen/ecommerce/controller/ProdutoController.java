package br.com.eullen.ecommerce.controller;

import br.com.eullen.ecommerce.entity.Estoque;
import br.com.eullen.ecommerce.entity.Produto;
import br.com.eullen.ecommerce.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Retorna lista de produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de produtos cadastrados"),
            @ApiResponse(code = 403, message = "Acesso não autorizado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno no servidor"),
    })
    @GetMapping( value = {"", "/"}, produces="application/json")
    public Iterable<Produto> recuperarTodos(){
        return this.produtoService.recuperarTodosOsProdutos();
    }

    @ApiOperation(value = "Retorna produto com o id informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o produto"),
            @ApiResponse(code = 400, message = "Produto com o id informado não encontrado"),
            @ApiResponse(code = 403, message = "Acesso não autorizado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno no servidor"),
    })
    @GetMapping ( value = "/{id}", produces="application/json")
    public Produto recuperarPorId(@PathVariable Long id) {
        return this.produtoService.recuperarProduto(id);
    }

    @PostMapping (produces="application/json")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto produtoCriado = this.produtoService.criarProduto(produto);
        return new ResponseEntity<Produto>(produtoCriado, HttpStatus.CREATED);
    }

    @PatchMapping (value = "/{id}/estoque", produces="application/json")
    public ResponseEntity<Produto> atualizarEstoqueProduto(@RequestBody Estoque estoque, @PathVariable Long id) {
        Produto produtoAtualizado = this.produtoService.atualizarEstoque(id,estoque.getQuantidade());
        return new ResponseEntity<Produto>(produtoAtualizado, HttpStatus.OK);
    }

    @PutMapping (value = "/{id}", produces="application/json")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto, @PathVariable Long id) {
        Produto produtoAtualizado = this.produtoService.salvarProduto(produto);
        return new ResponseEntity<Produto>(produtoAtualizado, HttpStatus.OK);
    }

}
