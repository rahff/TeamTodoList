import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { CreateTodoRequest } from 'src/core/application/todo/dto/CreateTodoFormData';
import { CreateTodoListRequest, DeleteTodoRequest } from 'src/core/application/todo/dto/CreateTodoListFormData';
import { TodoCommandHandler } from 'src/core/application/todo/spi/TodoCommandHandler';
import { Todo } from 'src/core/model/todo/Todo';
import { TodoList } from 'src/core/model/todo/TodoList';
import { environment } from 'src/environments/environment';
import {DoneTodoRequest} from "../../../../core/application/todo/dto/DoneTodoRequest";

@Injectable({
  providedIn: 'root'
})
export class TodoCommandService implements TodoCommandHandler {

  private baseServerUrl: string = environment.serverUrl;

  public constructor(private http: HttpClient) { }

  public createTodoList(formData: CreateTodoListRequest): Observable<TodoList> {
    return this.http.post<TodoList>(this.baseServerUrl+"/create-todoList", formData);
  }

  public addTodo(formData: CreateTodoRequest): Observable<Todo> {
    return this.http.post<Todo>(this.baseServerUrl+"/add-todo", formData);
  }

  public doneTodo(request: DoneTodoRequest): Observable<string> {
    return this.http.put<{id: string}>(this.baseServerUrl+"/done-todo", request)
    .pipe(map((json) => json.id));
  }

  public deleteTodo(deleteTodoRequest: DeleteTodoRequest): Observable<string> {
    return this.http.put<{id: string}>(this.baseServerUrl+"/delete-todo", deleteTodoRequest)
    .pipe(map((json) => json.id));
  }

  public deleteTodoList(todoListId: string): Observable<string> {
    return this.http.delete<{id: string}>(this.baseServerUrl+`/delete-todo-list/${todoListId}`)
    .pipe(map((json) => json.id));
  }
}
