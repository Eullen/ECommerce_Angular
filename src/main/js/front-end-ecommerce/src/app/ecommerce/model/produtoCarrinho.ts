import { Produto } from './produto';
export class ProdutoCarrinho {
  constructor(private produto: Produto, private quantidade: number) {}

  setQuantidade(quantidade: number) {
    this.quantidade = quantidade;
  }
}
