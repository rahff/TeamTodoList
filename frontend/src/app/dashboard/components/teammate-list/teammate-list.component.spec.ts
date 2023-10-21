import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeammateListComponent } from './teammate-list.component';

describe('TeammateListComponent', () => {
  let component: TeammateListComponent;
  let fixture: ComponentFixture<TeammateListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeammateListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeammateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
