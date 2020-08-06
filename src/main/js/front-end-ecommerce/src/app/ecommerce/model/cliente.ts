export class Cliente {
  constructor(
    public id: number,
    private nome: string,
    private usuario: string,
    public idCarrinho: number,
    public token?: string
  ) {}

  getToken() {
    return this.token;
  }

  setToken(token: string) {
    this.token = token;
  }

  getIdCarrinho() {
    return this.idCarrinho;
  }
}
