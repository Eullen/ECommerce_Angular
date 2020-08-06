import { Component, OnInit } from '@angular/core';
import { ProdutoCarrinho } from '../../model/produtoCarrinho';
import { Carrinho } from '../../model/carrinho';
import { CarrinhoService } from '../../service/carrinhoService';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Produto } from '../../model/produto';
import { AuthService } from '../../service/authService';
import { Cliente } from '../../model/cliente';
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
    this.carregando = true;
    this.carrinhoService.recuperarCarrinho().subscribe(
      carrinho => {
        this.carrinho = carrinho;
        this.resetFeedbacks();
      },
      error => this.handlerError(error, 'Erro ao recuperar os produtos. Tente novamente mais tarde.')
    );
  }

  atualizarQuantidade(event, produtoCarrinho: ProdutoCarrinho): void {
    console.log(event.target.value);
  }

  fecharPedido(carrinho: Carrinho): void {
    this.historicoPedidoService.salvarHistoricoPedido(carrinho).subscribe(
      data => {
        this.router.navigate(['/pedidos']);
      },
      error =>
        this.handlerError(
          error,
          error.mensagem ? error.mensagem : 'Ocorreu um problema ao finalizar seu pedido. Tente novamente mais tarde.'
        )
    );
  }

  private handlerError(error, mensagemErro) {
    console.log('Erro', error);
    this.resetFeedbacks();
    this.erro = mensagemErro;
  }

  resetFeedbacks() {
    this.carregando = false;
    this.sucesso = '';
    this.erro = '';
  }
}
