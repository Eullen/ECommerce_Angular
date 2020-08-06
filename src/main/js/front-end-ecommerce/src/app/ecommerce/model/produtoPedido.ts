export class ProdutoPedido {
  constructor(
    private id: number,
    private nome: string,
    private descricao: string,
    private quantidade: number,
    private valor: number,
    private total: number
  ) {}
}
