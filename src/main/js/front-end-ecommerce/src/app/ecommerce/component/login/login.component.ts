import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthService } from '../../service/authService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    if (this.authService.usuarioAtualValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      usuario: ['', Validators.required],
      senha: ['', Validators.required],
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authService.login(this.f.usuario.value, this.f.senha.value).subscribe(
      responseValue => {
        this.authService.recuperarClienteAuth(responseValue).subscribe(
          data => {
            this.router.navigate(['/']);
          },
          error => {
            this.handleError(error, 'Não foi possível completar o login. Erro ao salvar usuário.');
            this.authService.logout();
          }
        );
      },
      err => {
        this.handleError(
          err,
          err.status === 401
            ? 'Falha ao autenticar com o usuário/senha informados.'
            : 'Erro Interno. Tente novamente mais tarde.'
        );
      }
    );
  }

  handleError(erro, mensagem) {
    console.log(erro);
    this.loading = false;
    alert(mensagem);
  }
}
