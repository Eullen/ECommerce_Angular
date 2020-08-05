import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Cliente } from '../model/';

@Injectable({
  providedIn: 'root',
})
export class CarrinhoService {
  private urlCarrinhos = `${environment.apiUrl}/carrinhos`;

  constructor(private http: HttpClient) {}

  errorHandler(err) {
    console.log('Erro:', err);
    return throwError(err ? err.error : err);
  }
}
