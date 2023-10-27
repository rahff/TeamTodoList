import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { Todo } from 'src/core/application/todo/dto/Todo';
import { TodoList } from 'src/core/application/todo/dto/TodoList';
import { TodoListsByUserId} from 'src/core/application/todo/query/TodoListsByUserId';
import { todoListDeletedEvent, todoListsReceivedEvent } from 'src/core/store/todo-lists/Events';
import { TodoListsStoreApi } from 'src/core/store/todo-lists/TodoListStoreApi';
import { TodoListsViewModel } from 'src/core/store/todo-lists/TodoListsState';


@Component({
  selector: 'app-todo-lists-view',
  templateUrl: './todo-lists-view.component.html',
  styleUrls: ['./todo-lists-view.component.css']
})
export class TodoListsViewComponent implements OnInit, OnDestroy {

  public personalTodoLists$!: Observable<TodoList[]>;
  private subscription!: Subscription;
  
  constructor(private storeApi: TodoListsStoreApi, 
              private todoLists: TodoListsByUserId) { }

  ngOnInit(): void {
    this.personalTodoLists$ = this.storeApi.getListOfTodoList();
    this.subscription = this.todoLists.query().subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(result: Result<TodoListsViewModel>): void {
    if(result.isOk()) this.storeApi.fireEvent(todoListsReceivedEvent(result.getValue()));
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }

  public onTodoListDeletedEvent(id: string): void {
    this.storeApi.fireEvent(todoListDeletedEvent(id));
  }

}
