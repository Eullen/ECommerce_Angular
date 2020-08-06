import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthService } from '../service/authService';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private router: Router, private authService: AuthService) {}

  //coloca token no header da requisição se o usuário tiver logado na aplicação
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const usuarioAtual = this.authService.usuarioAtualValue;
    const isLogado = usuarioAtual && usuarioAtual.token;
    const isUrlApi = request.url.startsWith(environment.apiUrl);
    if (isLogado && isUrlApi) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${usuarioAtual.token}`,
          'Content-Type': 'application/json',
        },
      });
    }

    return next.handle(request);
  }
}
