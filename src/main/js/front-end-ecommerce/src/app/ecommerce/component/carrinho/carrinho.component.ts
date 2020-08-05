import { Component, OnInit } from '@angular/core';
import { ProdutoCarrinho } from '../../model/produtoCarrinho';
import { Carrinho } from '../../model/carrinho';
import { CarrinhoService } from '../../service/carrinhoService';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Produto } from '../../model/produto';
import { AuthService } from '../../service/authService';
import { Cliente } from '../../model/cliente';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss'],
})
export class CarrinhoComponent implements OnInit {
  usuarioLogado: Cliente;

  constructor(private carrinhoService: CarrinhoService, private authService: AuthService) {
    this.authService.usuarioAtual.subscribe(usuarioAtual => (this.usuarioLogado = usuarioAtual));
  }
  erro: boolean = false;

  ngOnInit(): void {
    this.recuperarCarrinho(this.usuarioLogado.getIdCarrinho());
  }

  totalCarrinho: 0;
  carrinho$: Observable<Carrinho>;

  recuperarCarrinho(idCarrinho: number): void {
    this.carrinho$ = this.carrinhoService.recuperarCarrinho(idCarrinho).pipe(
      catchError(err => {
        this.erro = true;
        return throwError(err);
      })
    );
  }

  atualizarQuantidade(event, produtoCarrinho: ProdutoCarrinho): void {
    produtoCarrinho.setQuantidade(event.target.value);
  }

  fecharPedido(carrinho: Carrinho): void {
    alert('bla');
  }
}
