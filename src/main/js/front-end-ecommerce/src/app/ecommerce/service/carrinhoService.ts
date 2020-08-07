import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { ProdutoCarrinho } from '../model/produtoCarrinho';
import { Carrinho } from '../model/carrinho';
import { Produto } from '../model/produto';
import { AuthService } from './authService';

@Injectable({
  providedIn: 'root',
})
export class CarrinhoService {
  private urlCarrinhos = `${environment.apiUrl}/carrinhos`;

  constructor(private http: HttpClient, private authService: AuthService) {}

  recuperarCarrinho(): Observable<Carrinho> {
    return this.http.get<Carrinho>(`${this.urlCarrinhos}/${this.authService.usuarioAtualValue.idCarrinho}`);
  }

  adicionarProdutoNoCarrinho(produto: Produto, quantidade: number): Observable<Carrinho> {
    return this.http.post<Carrinho>(
      `${this.urlCarrinhos}/${this.authService.usuarioAtualValue.idCarrinho}/produtos`,
      new ProdutoCarrinho(produto, quantidade)
    );
  }

  atualizarProdutoNoCarrinho(produto: Produto, quantidade: number): Observable<Carrinho> {
    return this.http.put<Carrinho>(
      `${this.urlCarrinhos}/${this.authService.usuarioAtualValue.idCarrinho}/produtos`,
      new ProdutoCarrinho(produto, quantidade)
    );
  }

  removerProduto(produto: Produto): Observable<Carrinho> {
    return this.http.delete<Carrinho>(
      `${this.urlCarrinhos}/${this.authService.usuarioAtualValue.idCarrinho}/produtos/${produto.id}`
    );
  }
}
