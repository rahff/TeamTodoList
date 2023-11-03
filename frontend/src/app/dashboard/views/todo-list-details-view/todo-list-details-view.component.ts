import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { TodoListById } from 'src/core/application/todo/query/TodoListById';
import { Todo } from 'src/core/model/todo/Todo';
import { todoAddedEvent, todoDeletedEvent, todoDonedEvent, todoListDetailsReceivedEvent } from 'src/core/store/todoList-details/Events';
import { TodoListDetails, TodoListDetailsViewModel } from 'src/core/store/todoList-details/TodoListDetailsState';
import { TodoListDetailsStoreApi } from 'src/core/store/todoList-details/TodoListDetailsStoreApi';



@Component({
  selector: 'app-todo-list-details',
  templateUrl: './todo-list-details-view.component.html',
  styleUrls: ['./todo-list-details-view.component.css']
})
export class TodoListDetailsViewComponent implements OnInit, OnDestroy {

  public todoListDetails$!: Observable<TodoListDetails>;
  private subscription = new Subscription();

  constructor(private storeApi: TodoListDetailsStoreApi, 
              private todoListById: TodoListById,
              private activatedRoute: ActivatedRoute) { }

  public ngOnInit(): void {
    const todoListId = this.getTodoListIdRouteParameter();
    this.todoListDetails$ = this.storeApi.getTodoListDetails();
    this.subscription.add(this.todoListById.query(todoListId).subscribe({
      next: this.onQueryResult.bind(this)
    }))
  }

  private getTodoListIdRouteParameter(): string {
    return this.activatedRoute.snapshot.paramMap.get("id") || "";
  }

  private onQueryResult(result: Result<TodoListDetailsViewModel>): void {
    if(result.isOk()) 
      this.storeApi.fireEvent(todoListDetailsReceivedEvent(result.getValue()));
  }

  public onTodoDonedEvent(id: string): void {
    this.storeApi.fireEvent(todoDonedEvent(id));
  }

  public onTodoDeletedEvent(id: string): void {
    this.storeApi.fireEvent(todoDeletedEvent(id));
  }

  public onTodoAddedEvent(todo: Todo): void {
    this.storeApi.fireEvent(todoAddedEvent(todo));
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }

}
