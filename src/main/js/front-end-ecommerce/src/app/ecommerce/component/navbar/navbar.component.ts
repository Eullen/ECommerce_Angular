import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from '../../model/cliente';
import { AuthService } from '../../service/authService';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  usuarioLogado: Cliente;

  constructor(private router: Router, private authService: AuthService) {
    this.authService.usuarioAtual.subscribe(usuarioAtual => (this.usuarioLogado = usuarioAtual));
  }

  ngOnInit(): void {}

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
