import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { Cliente } from '../model/cliente';
import { environment } from '../../../environments/environment';
import { map, catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

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
    return this.http
      .post<any>(`${environment.apiUrl}/cliente/login`, { usuario, senha })
      .pipe(
        map(cliente => {
          //guarda usuario e token no local storage
          localStorage.setItem('usuarioAtual', JSON.stringify(cliente));
          this.usuarioAtualSubject.next(cliente);
          return cliente;
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
