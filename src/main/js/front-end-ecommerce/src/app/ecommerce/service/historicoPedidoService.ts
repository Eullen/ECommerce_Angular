import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { HistoricoPedido } from '../model/historicoPedido';

@Injectable({
  providedIn: 'root',
})
export class HistoricoPedidoService {
  getUrlHistoricoPedido(idCliente: number) {
    return `${environment.apiUrl}/cliente/${idCliente}/historicoPedidos`;
  }

  constructor(private http: HttpClient) {}

  recuperarHistoricoPedidos(idCarrinho: number): Observable<HistoricoPedido[]> {
    return null;
  }

  errorHandler(err) {
    console.log('Erro:', err);
    return throwError(err ? err.error : err);
  }
}
