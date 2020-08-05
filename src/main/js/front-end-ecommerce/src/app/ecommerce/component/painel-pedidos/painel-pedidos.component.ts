import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HistoricoPedidoService } from '../../service/historicoPedidoService';
import { HistoricoPedido } from '../../model/historicoPedido';

@Component({
  selector: 'app-painel-pedidos',
  templateUrl: './painel-pedidos.component.html',
  styleUrls: ['./painel-pedidos.component.scss'],
})
export class PainelPedidosComponent implements OnInit {
  constructor(private historicoPedidoService: HistoricoPedidoService) {}

  ngOnInit(): void {
    this.recuperarHistoricoPedidos();
  }

  erro: boolean = true;
  idCliente = 3;
  historicoPedidos$: Observable<HistoricoPedido[]>;

  recuperarHistoricoPedidos(): void {
    this.historicoPedidos$ = this.historicoPedidoService.recuperarHistoricoPedidos(3);
  }
}
