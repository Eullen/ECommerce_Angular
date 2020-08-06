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

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient, private authService: AuthService) {}

  recuperarCarrinho(idCarrinho: number): Observable<Carrinho> {
    return this.http.get<Carrinho>(`${this.urlCarrinhos}/${idCarrinho}`).pipe(retry(1), catchError(this.errorHandler));
  }

  adicionarProdutoNoCarrinho(produto: Produto, quantidade: number): Observable<any> {
    const idCarrinho = this.authService.usuarioAtualValue.idCarrinho;
    return this.http
      .post(`${this.urlCarrinhos}/${idCarrinho}/produtos`, new ProdutoCarrinho(produto, quantidade), this.httpOptions)
      .pipe(retry(1), catchError(this.errorHandler));
  }

  errorHandler(err) {
    console.log('Erro:', err);
    return throwError(err ? err.error : err);
  }
}
