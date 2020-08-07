import { Estoque } from './estoque';

export class Produto {
  constructor(
    public id: number,
    public nome: string,
    public valor: number,
    public descricao: string,
    public estoque: Estoque
  ) {}
}
