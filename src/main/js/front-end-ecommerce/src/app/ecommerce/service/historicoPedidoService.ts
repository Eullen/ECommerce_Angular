import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { HistoricoPedido } from '../model/historicoPedido';
import { Carrinho } from '../model/carrinho';
import { AuthService } from './authService';

@Injectable({
  providedIn: 'root',
})
export class HistoricoPedidoService {
  constructor(private http: HttpClient, private authService: AuthService) {}

  getUrlHistoricoPedido() {
    const idCliente = this.authService.usuarioAtualValue.id;
    return `${environment.apiUrl}/clientes/${idCliente}/historicoPedidos`;
  }

  recuperarHistoricoPedidos(): Observable<HistoricoPedido[]> {
    return this.http.get<HistoricoPedido[]>(this.getUrlHistoricoPedido());
  }

  salvarHistoricoPedido(carrinho: Carrinho): Observable<HistoricoPedido> {
    return this.http.post<HistoricoPedido>(this.getUrlHistoricoPedido(), carrinho);
  }
}
