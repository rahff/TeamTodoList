import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { TodoList } from 'src/core/application/todo/dto/TodoList';
import { TodoListsByUserId} from 'src/core/application/todo/query/TodoListsByUserId';
import { todoListsReceivedEvent } from 'src/core/store/todo-lists/Events';
import { TodoListsStoreApi } from 'src/core/store/todo-lists/TodoListStoreApi';
import { TodoListsView } from 'src/core/store/todo-lists/TodoListsState';


@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListsComponent implements OnInit, OnDestroy {

  public personalTodoLists$!: Observable<TodoList[]>;
  private subscription!: Subscription;
  
  constructor(private selector: TodoListsStoreApi, 
              private todoLists: TodoListsByUserId) { }

  ngOnInit(): void {
    this.personalTodoLists$ = this.selector.getListOfTodoList();
    this.subscription = this.todoLists.query().subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(result: Result<TodoListsView>): void {
    if(result.isOk()) this.selector.fireEvent(todoListsReceivedEvent(result.getValue()));
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }

}
