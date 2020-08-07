import { Component, OnInit } from '@angular/core';

import { ProdutoService } from '../../service/produtoService';
import { CarrinhoService } from '../../service/carrinhoService';
import { AuthService } from '../../service/authService';
import { Produto } from '../../model/produto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-painel-produtos',
  templateUrl: './painel-produtos.component.html',
  styleUrls: ['./painel-produtos.component.scss'],
})
export class PainelProdutosComponent implements OnInit {
  constructor(
    private produtoService: ProdutoService,
    private carrinhoService: CarrinhoService,
    private authService: AuthService
  ) {}

  produtos: Produto[] = [];
  carregando = false;
  erro = '';
  sucesso = '';
  trechoNome = '';

  ngOnInit(): void {
    this.recuperarTodosOsProdutos();
  }

  recuperarTodosOsProdutos(): void {
    this.carregando = true;
    this.produtoService.recuperarTodosOsProdutos().subscribe(
      produtos => {
        this.produtos = produtos;
        this.resetFeedbacks();
      },
      error => this.handlerError(error, 'Erro ao recuperar os produtos. Tente novamente mais tarde.')
    );
  }

  recuperarProdutosPorNome(): void {
    this.carregando = true;
    this.produtoService.recuperarProdutoPorNome(this.trechoNome).subscribe(
      produtos => {
        this.produtos = produtos;
        this.resetFeedbacks();
      },
      error => this.handlerError(error, 'Erro ao recuperar os produtos. Tente novamente mais tarde.')
    );
  }

  private handlerError(error, mensagemErro) {
    console.log('Erro', error);
    this.resetFeedbacks();
    this.erro = mensagemErro;
  }

  adicionarProdutoAoCarrinho(produto: Produto, quantidade: number) {
    if (this.authService.usuarioAtualValue) {
      this.carregando = true;
      this.carrinhoService.adicionarProdutoNoCarrinho(produto, quantidade).subscribe(
        data => {
          this.resetFeedbacks();
          this.sucesso = 'Produto adicionado no carrinho com sucesso';
        },
        err => {
          this.handlerError(
            err,
            err.error.mensagem ? err.error.mensagem : 'Ocorreu um problema ao adicionar seu produto ao carrinho'
          );
        }
      );
    } else {
      this.erro = 'É necessário estar logado para adicionar produtos ao carrinho.';
    }
  }

  resetFeedbacks() {
    this.carregando = false;
    this.sucesso = '';
    this.erro = '';
  }

  setTrechoBuscaNome(event) {
    this.trechoNome = event.target.value;
  }
}
