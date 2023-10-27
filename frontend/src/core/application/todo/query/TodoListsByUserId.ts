import { Observable, catchError, map } from "rxjs";
import { TodoList } from "../dto/TodoList";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";
import { TodoListsViewModel } from "src/core/store/todo-lists/TodoListsState";

export class TodoListsByUserId extends Query<TodoListsViewModel>{
    
    public constructor(private queryHandler: TodoQueryHandler, 
                       private userContextHolder: UserContextHolder){super()}

    public query(): Observable<Result<TodoListsViewModel>> {
        const userId = this.userContextHolder.getUserId();
        return this.queryHandler.getTodoListByRef(userId)
        .pipe(map(this.onSuccess),
         catchError(this.onError));
    }
}

