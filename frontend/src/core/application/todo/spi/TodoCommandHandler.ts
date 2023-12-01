import { CreateTodoListRequest, DeleteTodoRequest } from "../dto/CreateTodoListFormData";
import { Observable } from "rxjs";
import { TodoList } from "../../../model/todo/TodoList";
import { CreateTodoRequest } from "../dto/CreateTodoFormData";
import { Todo } from "../../../model/todo/Todo";
import {DoneTodoRequest} from "../dto/DoneTodoRequest";



export interface TodoCommandHandler {
    createTodoList(formData: CreateTodoListRequest): Observable<TodoList>;
    addTodo(formData: CreateTodoRequest): Observable<Todo>;
    doneTodo(todoId: DoneTodoRequest): Observable<string>;
    deleteTodo(deleteTodoRequest: DeleteTodoRequest): Observable<string>;
    deleteTodoList(todoListId: string): Observable<string>;
}