import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Produto } from '../model/produto';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  private urlProdutos = `${environment.apiUrl}/produtos`;

  constructor(private http: HttpClient) {}

  recuperarTodosOsProdutos(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.urlProdutos);
  }

  recuperarProdutoPorNome(trechoNomeProduto: string): Observable<Produto[]> {
    let params = new HttpParams();
    params = params.append('trechoNome', trechoNomeProduto);

    return this.http.get<Produto[]>(this.urlProdutos, { params });
  }
}
