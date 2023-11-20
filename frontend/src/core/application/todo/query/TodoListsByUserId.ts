import { Observable, catchError, map } from "rxjs";

import { TodoListsViewModel } from "src/core/store/todo-lists/TodoListsState";
import { Query } from "../../shared/query/Query";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";
import { Result } from "../../shared/dto/Result";
import { UnAuthenticatedException } from "../../shared/execptions/UnAuthenticatedException";

export class TodoListsByUserId extends Query<TodoListsViewModel>{
    
    public constructor(private queryHandler: TodoQueryHandler, 
                       private userContextHolder: UserContextHolder){super()}

    public query(): Observable<Result<TodoListsViewModel>> {
        const userId = this.userContextHolder.getUserId();
        if(!userId) return this.onError(new UnAuthenticatedException());
        return this.queryHandler.getTodoListByRef(userId)
        .pipe(map(this.onSuccess),
         catchError(this.onError));
    }
}

