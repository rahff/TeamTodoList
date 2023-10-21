import { TodoListsByUserId } from 'src/core/application/todo/query/TodoListsByUserId';
import { TodoListsComponent } from './todo-list.component';
import { InMemoryTodoQueryHandler } from 'src/core/application/todo/in-memory/InMemoryTodoQueryHandler';
import { fakeAsync, tick } from '@angular/core/testing';
import { TodoList } from 'src/core/application/todo/dto/TodoList';
import { todoListsForTeammateRef } from 'src/core/application/todo/in-memory/data/data.todo';
import { createStore } from 'src/core/store/Store';
import { FakeUserContextHolder } from 'src/core/application/team/in-memory/FakeUserContextHolder';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { TodoListsStoreApi } from 'src/core/store/todo-lists/TodoListStoreApi';




describe('TodoListComponent', () => {
  let component: TodoListsComponent;
  let query: TodoListsByUserId;
  let selector: TodoListsStoreApi;
  beforeEach(() => {
    selector = new TodoListsStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
    query = new TodoListsByUserId(new InMemoryTodoQueryHandler(), new FakeUserContextHolder());
    component = new TodoListsComponent(selector, query);
  });

  it('should hold personal todo lists state', fakeAsync(() => {
    component.ngOnInit();
    tick(509);
    component.personalTodoLists$.subscribe((lists: TodoList[])=> {
      expect(lists).toEqual(todoListsForTeammateRef);
    })
  }));
});
