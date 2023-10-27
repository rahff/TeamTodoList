import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoDropdownComponent } from './todo-dropdown.component';

describe('TodoDropdownComponent', () => {
  let component: TodoDropdownComponent;
  let fixture: ComponentFixture<TodoDropdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodoDropdownComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoDropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
