import { CreateTodoListRequest } from "../dto/CreateTodoListFormData";
import { Observable } from "rxjs";
import { TodoList } from "../../../model/todo/TodoList";
import { CreateTodoRequest } from "../dto/CreateTodoFormData";
import { Todo } from "../../../model/todo/Todo";



export interface TodoCommandHandler {
    createTodoList(formData: CreateTodoListRequest): Observable<TodoList>;
    getTodoListsByRef(ref: string): Observable<TodoList[]>;
    addTodo(formData: CreateTodoRequest): Observable<Todo>;
    doneTodo(todoId: string): Observable<string>;
    deleteTodo(todoId: string): Observable<string>;
    deleteTodoList(todoListId: string): Observable<string>;
}