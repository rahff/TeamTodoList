import { CreateTodoListRequest } from "../dto/CreateTodoListFormData";
import { Observable } from "rxjs";
import { TodoList } from "../dto/TodoList";
import { CreateTodoRequest } from "../dto/CreateTodoFormData";



export interface TodoCommandHandler {
    createTodo(formData: CreateTodoListRequest): Observable<true>;
    getTodoListsByRef(ref: string): Observable<TodoList[]>;
    addTodo(formData: CreateTodoRequest): Observable<true>;
    doneTodo(todoId: string): Observable<true>;
    deleteTodo(todoId: string): Observable<true>;
    deleteTodoListTodo(todoListId: string): Observable<true>;
}