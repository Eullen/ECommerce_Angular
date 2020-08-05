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
            alert('Não foi possível completar o login. Erro ao salvar usuário.');
            this.loading = false;
            this.authService.logout();
          }
        );
      },
      err => {
        //Mudar mensagem se o erro não foi de 401
        alert('Erro ao autenticar com o usuário/senha informados.');
        this.loading = false;
      }
    );
  }
}
