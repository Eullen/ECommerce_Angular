import { ProdutoCarrinho } from './produtoCarrinho';
export class Carrinho {
  constructor(private id: number, private produtosCarrinho: ProdutoCarrinho[]) {}
}
