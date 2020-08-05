export class Cliente {
  constructor(
    private id: number,
    private nome: string,
    private usuario: string,
    private idCarrinho: number,
    private token?: string
  ) {}

  getToken() {
    return this.token;
  }

  getIdCarrinho() {
    return this.idCarrinho;
  }
}
