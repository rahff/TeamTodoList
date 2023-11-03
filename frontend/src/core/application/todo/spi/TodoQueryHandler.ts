import { Observable } from "rxjs";
import { Todo } from "../../../model/todo/Todo";
import { TodoListDetailsViewModel } from "src/core/store/todoList-details/TodoListDetailsState";
import { TodoListsViewModel } from "src/core/store/todo-lists/TodoListsState";



export interface TodoQueryHandler {
    getTodoListByRef(ref: string): Observable<TodoListsViewModel>;
    getTodoListDetailById(id: string): Observable<TodoListDetailsViewModel>;
    getTodosByListId(id: string): Observable<Todo[]>;
}