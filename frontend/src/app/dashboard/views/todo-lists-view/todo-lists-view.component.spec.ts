import { TodoListsByUserId } from 'src/core/application/todo/query/TodoListsByUserId';
import { TodoListsViewComponent } from './todo-lists-view.component';
import { fakeAsync } from '@angular/core/testing';
import { TodoList } from 'src/core/application/todo/dto/TodoList';
import { todoListsForTeammateRef, todoListsForTeammateRefAfterDeletingOne } from 'src/core/application/todo/in-memory/data/data.todo';
import { createStore } from 'src/core/store/Store';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { TodoListsStoreApi } from 'src/core/store/todo-lists/TodoListStoreApi';
import { ngOnInitPast500Ms } from '../../root/views/utils/tests.utils';
import { InMemoryTodoQueryHandler } from '../../services/inMemory/InMemoryTodoQueryHandler';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';




describe('TodoListComponent', () => {
  let component: TodoListsViewComponent;
  let query: TodoListsByUserId;
  let selector: TodoListsStoreApi;
  beforeEach(() => {
    selector = new TodoListsStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
    query = new TodoListsByUserId(new InMemoryTodoQueryHandler(), new FakeUserContextHolder());
    component = new TodoListsViewComponent(selector, query);
  });

  it('should hold personal todo lists state', fakeAsync(() => {
    ngOnInitPast500Ms(component)
    component.personalTodoLists$.subscribe((lists: TodoList[])=> {
      expect(lists).toEqual(todoListsForTeammateRef);
    })
  }));

  it('should update lists state on todoListDeleted event', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTodoListDeletedEvent("rfjrjfkdkjdjdkmlzkj");
    component.personalTodoLists$.subscribe((lists: TodoList[])=> {
      expect(lists).toEqual(todoListsForTeammateRefAfterDeletingOne);
    })
  }));
});
