import { Pedido } from '../model/pedido';

export class HistoricoPedido {
  constructor(private id: number, private historicoPedidos: Pedido[]) {}
}
