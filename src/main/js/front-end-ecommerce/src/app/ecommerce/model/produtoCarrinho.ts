import { Produto } from './produto';
export class ProdutoCarrinho {
  constructor(public produto: Produto, public quantidade: number) {}
}
