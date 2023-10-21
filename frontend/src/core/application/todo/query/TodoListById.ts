import { Observable, catchError, map } from "rxjs";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { TodoListDetails } from "../dto/TodoListDetails";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";



export class TodoListById extends Query<TodoListDetails> {
    
    public constructor(private queryHandler: TodoQueryHandler){super()}

    public query(id: string): Observable<Result<TodoListDetails>> {
        return this.queryHandler.getTodoListDetailById(id)
        .pipe(map(this.onSuccess),
         catchError(this.onError));
    }
}