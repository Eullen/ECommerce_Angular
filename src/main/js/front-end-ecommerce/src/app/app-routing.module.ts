import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PainelProdutosComponent } from './ecommerce/component/painel-produtos/painel-produtos.component';
import { CarrinhoComponent } from './ecommerce/component/carrinho/carrinho.component';
import { PainelPedidosComponent } from './ecommerce/component/painel-pedidos/painel-pedidos.component';
import { LoginComponent } from './ecommerce/component/login/login.component';
import { AuthGuard } from './ecommerce/security/authGuard';

const routes: Routes = [
  { path: '', component: PainelProdutosComponent },
  { path: 'carrinho', component: CarrinhoComponent, canActivate: [AuthGuard] },
  { path: 'pedidos', component: PainelPedidosComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
