import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { ProdutoComponent } from './ecommerce/component/produto/produto.component';
import { NavbarComponent } from './ecommerce/component/navbar/navbar.component';
import { FooterComponent } from './ecommerce/component/footer/footer.component';
import { PainelProdutosComponent } from './ecommerce/component/painel-produtos/painel-produtos.component';
import { CarrinhoComponent } from './ecommerce/component/carrinho/carrinho.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PainelPedidosComponent } from './ecommerce/component/painel-pedidos/painel-pedidos.component';
import { LoginComponent } from './ecommerce/component/login/login.component';
import { JwtInterceptor } from './ecommerce/security/jwtInterceptor';
import { ErrorInterceptor } from './ecommerce/security/errorInterceptor';

@NgModule({
  declarations: [
    AppComponent,
    EcommerceComponent,
    ProdutoComponent,
    NavbarComponent,
    FooterComponent,
    PainelProdutosComponent,
    CarrinhoComponent,
    PainelPedidosComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
