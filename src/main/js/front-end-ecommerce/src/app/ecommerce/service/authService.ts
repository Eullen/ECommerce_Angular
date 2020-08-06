import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { Cliente } from '../model/cliente';
import { environment } from '../../../environments/environment';
import { map, catchError, mergeMap, tap } from 'rxjs/operators';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private usuarioAtualSubject: BehaviorSubject<Cliente>;
  public usuarioAtual: Observable<Cliente>;

  constructor(private http: HttpClient) {
    this.usuarioAtualSubject = new BehaviorSubject<Cliente>(JSON.parse(localStorage.getItem('usuarioAtual')));
    this.usuarioAtual = this.usuarioAtualSubject.asObservable();
  }

  public get usuarioAtualValue(): Cliente {
    return this.usuarioAtualSubject.value;
  }

  login(usuario: string, senha: string) {
    let params = new HttpParams();

    params = params.append('grant_type', environment.grantType);

    const payload = {
      username: usuario,
      password: senha,
    };

    const headers = {
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      Authorization: 'Basic ' + btoa(`${environment.clientId}:${environment.clientSecret}`),
    };

    return this.http.post<any>(`${environment.oauthUrl}`, new HttpParams({ fromObject: payload }), {
      headers,
      params,
    });
  }

  public recuperarClienteAuth(tokenResponse: any) {
    const headers = {
      Authorization: `${tokenResponse.token_type} ${tokenResponse.access_token}`,
    };
    return this.http
      .get<Cliente>(`${environment.apiUrl}/clientes/auth`, { headers })
      .pipe(
        tap(cliente => {
          //salvando cliente e token na local storage
          cliente.token = tokenResponse.access_token;
          localStorage.setItem('usuarioAtual', JSON.stringify(cliente));
          this.usuarioAtualSubject.next(cliente);
        }),
        catchError(err => {
          console.log(err);
          return throwError(err);
        })
      );
  }

  logout() {
    // remove dados do cliente da local storage
    localStorage.removeItem('usuarioAtual');
    this.usuarioAtualSubject.next(null);
  }
}
