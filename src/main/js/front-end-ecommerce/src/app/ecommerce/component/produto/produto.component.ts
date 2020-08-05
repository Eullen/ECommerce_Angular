import { Component, OnInit } from '@angular/core';
import { Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.scss'],
})
export class ProdutoComponent implements OnInit {
  @Input() produto;
  @Output() adicionarAoCarrinhoClick = new EventEmitter<any>();

  constructor() {}

  ngOnInit(): void {}
  quantidade: number = 1;

  onAdicionarAoCarrinhoClick() {
    this.adicionarAoCarrinhoClick.emit(this.quantidade);
  }

  setQuantidade(event) {
    this.quantidade = event.target.value;
  }
}
