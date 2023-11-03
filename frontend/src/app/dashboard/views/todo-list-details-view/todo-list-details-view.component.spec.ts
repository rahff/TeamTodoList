import { TodoListDetailsStoreApi } from 'src/core/store/todoList-details/TodoListDetailsStoreApi';
import { TodoListDetailsViewComponent } from './todo-list-details-view.component';
import { createStore } from 'src/core/store/Store';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { fakeAsync } from '@angular/core/testing';
import { FakeActivatedRoute, ngOnInitPast500Ms } from '../../root/views/utils/tests.utils';
import { TodoListDetails } from 'src/core/store/todoList-details/TodoListDetailsState';
import { InMemoryTodoQueryHandler } from '../../services/inMemory/InMemoryTodoQueryHandler';
import { TodoListById } from 'src/core/application/todo/query/TodoListById';
import { teammateTodoListDetails, todosFromTeammateTodoListAfterDoneTask2, todosFromTeammateTodoListAfterDeleteOne, newTodo } from 'src/core/application/todo/in-memory/data/data.todo';



describe('TodoListDetailsComponent', () => {
  let component: TodoListDetailsViewComponent;
  let storeApi: TodoListDetailsStoreApi
  let query: TodoListById;
  let activatedRoute: FakeActivatedRoute
  beforeEach(() => {
    activatedRoute = new FakeActivatedRoute("todoListId", "id");
    storeApi = new TodoListDetailsStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
    query = new TodoListById(new InMemoryTodoQueryHandler())
    component = new TodoListDetailsViewComponent(storeApi, query, activatedRoute);
  });

  it('should load the todoList details', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.todoListDetails$.subscribe((details: TodoListDetails) => {
      expect(details).toEqual(teammateTodoListDetails.viewModel)
    })
  }));

  it('should done the todo in the list', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTodoDonedEvent("hjszozjuejedkedjed")
    component.todoListDetails$.subscribe((details: TodoListDetails) => {
      expect(details.todos).toEqual(todosFromTeammateTodoListAfterDoneTask2)
    })
  }));

  it('should delete the todo in the list', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTodoDeletedEvent("hjszozjuejedkedjed")
    component.todoListDetails$.subscribe((details: TodoListDetails) => {
      expect(details.todos).toEqual(todosFromTeammateTodoListAfterDeleteOne)
    })
  }));

  it('should add the todo in the list', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTodoAddedEvent(newTodo)
    component.todoListDetails$.subscribe((details: TodoListDetails) => {
      expect(details.todos).toEqual([...teammateTodoListDetails.viewModel.todos, newTodo])
    })
  }));
});
