import { Component, OnInit } from '@angular/core';
import { ProdutoCarrinho } from '../../model/produtoCarrinho';
import { Carrinho } from '../../model/carrinho';
import { CarrinhoService } from '../../service/carrinhoService';
import { Observable } from 'rxjs';
import { AuthService } from '../../service/authService';
import { HistoricoPedidoService } from '../../service/historicoPedidoService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss'],
})
export class CarrinhoComponent implements OnInit {
  constructor(
    private router: Router,
    private carrinhoService: CarrinhoService,
    private authService: AuthService,
    private historicoPedidoService: HistoricoPedidoService
  ) {}

  carregando: boolean = false;
  erro = '';
  sucesso = '';
  totalCarrinho: 0;
  carrinho: Carrinho;

  ngOnInit(): void {
    this.recuperarCarrinho();
  }

  recuperarCarrinho(): void {
    this.atualizarCarrinho(this.carrinhoService.recuperarCarrinho());
  }

  atualizarQuantidade(event, produtoCarrinho: ProdutoCarrinho): void {
    this.atualizarCarrinho(
      this.carrinhoService.atualizarProdutoNoCarrinho(produtoCarrinho.produto, event.target.value)
    );
    console.log(event.target.value);
  }

  atualizarCarrinho(carrinho: Observable<Carrinho>) {
    this.carregando = true;
    carrinho.subscribe(
      carrinho => {
        this.carrinho = carrinho;
        this.resetFeedbacks();
      },
      error => this.handlerError(error, 'Erro ao atualizar carrinho.')
    );
  }

  fecharPedido(carrinho: Carrinho): void {
    this.historicoPedidoService.salvarHistoricoPedido(carrinho).subscribe(
      data => {
        this.router.navigate(['/pedidos']);
      },
      error => this.handlerError(error, 'Ocorreu um problema ao finalizar seu pedido. Tente novamente mais tarde.')
    );
  }

  removerProduto(produtoCarrinho: ProdutoCarrinho) {
    this.atualizarCarrinho(this.carrinhoService.removerProduto(produtoCarrinho.produto));
  }

  resetFeedbacks() {
    this.carregando = false;
    this.sucesso = '';
    this.erro = '';
  }

  private handlerError(error, mensagemErro) {
    console.log('Erro', error);
    this.resetFeedbacks();
    this.erro = error.error.mensagem ?? mensagemErro;
  }
}
