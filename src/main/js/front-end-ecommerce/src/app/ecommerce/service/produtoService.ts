import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Produto } from '../model/produto';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  private urlProdutos = `${environment.apiUrl}/produtos`;

  constructor(private http: HttpClient) {}

  recuperarTodosOsProdutos(): Observable<Produto[]> {
    return this.http
      .get<Produto[]>(this.urlProdutos)
      .pipe(catchError(this.handleError<any>('recuperarTodosOsProdutos', [])));
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
