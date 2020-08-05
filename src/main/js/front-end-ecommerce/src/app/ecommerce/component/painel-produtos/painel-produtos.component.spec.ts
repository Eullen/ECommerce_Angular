import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PainelProdutosComponent } from './painel-produtos.component';

describe('PainelProdutosComponent', () => {
  let component: PainelProdutosComponent;
  let fixture: ComponentFixture<PainelProdutosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PainelProdutosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PainelProdutosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
