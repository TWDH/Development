import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductcontentComponent } from './productcontent.component';

describe('ProductcontentComponent', () => {
  let component: ProductcontentComponent;
  let fixture: ComponentFixture<ProductcontentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductcontentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductcontentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
