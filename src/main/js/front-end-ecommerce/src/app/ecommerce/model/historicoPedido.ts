import { ProdutoPedido } from '../model/produtoPedido';

export class HistoricoPedido {
  constructor(
    private id: number,
    private dataCadastro: Date,
    private total: number,
    private produtosPedidos: ProdutoPedido[]
  ) {}
}
