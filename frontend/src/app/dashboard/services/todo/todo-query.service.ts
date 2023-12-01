import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TodoQueryHandler } from 'src/core/application/todo/spi/TodoQueryHandler';
import { TodoListsViewModel } from 'src/core/store/todo-lists/TodoListsState';
import { TodoListDetailsViewModel } from 'src/core/store/todoList-details/TodoListDetailsState';
import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class TodoQueryService implements TodoQueryHandler {

  private baseServerUrl: string = environment.serverUrl;

  public constructor(private http: HttpClient) {}

  public getTodoListByRef(ref: string): Observable<TodoListsViewModel> {
    return this.http.get<TodoListsViewModel>(this.baseServerUrl+`/todoLists/${ref}`);
  }

  public getTodoListDetailById(id: string): Observable<TodoListDetailsViewModel> {
    return this.http.get<TodoListDetailsViewModel>(this.baseServerUrl+`/todo-list-details/${id}`);
  }
}
