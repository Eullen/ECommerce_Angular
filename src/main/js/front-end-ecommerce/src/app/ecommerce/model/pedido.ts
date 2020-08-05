import { Produto } from '../model/produto';

export class Pedido {
  constructor(id: number, dataCriacao: Date, produtos: Produto[]) {}
}
