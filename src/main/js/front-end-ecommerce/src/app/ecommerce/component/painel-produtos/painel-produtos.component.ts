import { Component, OnInit } from '@angular/core';

import { ProdutoService } from '../../service/produtoService';
import { CarrinhoService } from '../../service/carrinhoService';
import { Produto } from '../../model/produto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-painel-produtos',
  templateUrl: './painel-produtos.component.html',
  styleUrls: ['./painel-produtos.component.scss'],
})
export class PainelProdutosComponent implements OnInit {
  constructor(private produtoService: ProdutoService, private carrinhoService: CarrinhoService) {}

  ngOnInit(): void {
    this.recuperarProdutos();
  }

  produtos$: Observable<Produto[]>;

  recuperarProdutos(): void {
    this.produtos$ = this.produtoService.recuperarTodosOsProdutos();
  }

  adicionarProdutoAoCarrinho(produto: Produto, quantidade: number) {
    this.carrinhoService.adicionarProdutoNoCarrinho(produto, quantidade).subscribe(
      data => {
        alert('Produto adicionado no carrinho com sucesso');
      },
      erro => {
        alert(erro.mensagem ? erro.mensagem : 'Ocorreu um problema ao adicionar seu produto ao carrinho');
      }
    );
  }
}
