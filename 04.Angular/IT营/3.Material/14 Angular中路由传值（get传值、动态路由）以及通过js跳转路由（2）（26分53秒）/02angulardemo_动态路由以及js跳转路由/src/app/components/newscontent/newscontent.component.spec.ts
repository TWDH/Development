import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewscontentComponent } from './newscontent.component';

describe('NewscontentComponent', () => {
  let component: NewscontentComponent;
  let fixture: ComponentFixture<NewscontentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewscontentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewscontentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
