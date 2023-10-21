import { Observable, catchError, map } from "rxjs";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { Todo } from "../dto/Todo";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";



export class TodosByListId extends Query<Todo[]> {
    
    public constructor(private queryHandler: TodoQueryHandler){super()}

    public query(listId: string): Observable<Result<Todo[]>> {
        return this.queryHandler.getTodosByListId(listId)
        .pipe(map(this.onSuccess),
         catchError(this.onError));
    }
}