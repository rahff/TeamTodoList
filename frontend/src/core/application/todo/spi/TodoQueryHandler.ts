import { Observable } from "rxjs";
import { Todo } from "../dto/Todo";
import { TodoListDetailsView } from "src/core/store/todoList-details/TodoListDetailsState";
import { TodoListsView } from "src/core/store/todo-lists/TodoListsState";



export interface TodoQueryHandler {
    getTodoListByRef(ref: string): Observable<TodoListsView>;
    getTodoListDetailById(id: string): Observable<TodoListDetailsView>;
    getTodosByListId(id: string): Observable<Todo[]>;
}