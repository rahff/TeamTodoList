import { TodoListsComponent } from './todo-list.component';

describe('TodoListComponent', () => {
  let component: TodoListsComponent;

  beforeEach(() => {
    component = new TodoListsComponent();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
