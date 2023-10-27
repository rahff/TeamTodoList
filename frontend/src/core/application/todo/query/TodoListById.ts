import { Observable, catchError, map } from "rxjs";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";
import { TodoListDetailsViewModel } from "src/core/store/todoList-details/TodoListDetailsState";



export class TodoListById extends Query<TodoListDetailsViewModel> {
    
    public constructor(private queryHandler: TodoQueryHandler){super()}

    public query(id: string): Observable<Result<TodoListDetailsViewModel>> {
        return this.queryHandler.getTodoListDetailById(id)
        .pipe(map(this.onSuccess),
         catchError(this.onError));
    }
}