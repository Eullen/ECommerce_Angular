import { Estoque } from './estoque';

export class Produto {
  constructor(
    private id: number,
    private nome: string,
    private valor: number,
    private descricao: string,
    private estoque: Estoque
  ) {}
}
