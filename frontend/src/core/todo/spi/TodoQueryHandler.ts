import { Observable } from "rxjs";
import { TodoList } from "../dto/TodoList";
import { Todo } from "../dto/Todo";
import { TodoListDetails } from "../dto/TodoListDetails";

export interface TodoQueryHandler {
    getTodoListByRef(ref: string): Observable<TodoList[]>;
    getTodoListDetailById(id: string): Observable<TodoListDetails>;
    getTodosByListId(id: string): Observable<Todo[]>;
}