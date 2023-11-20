import { Command } from "src/core/application/shared/command/Command";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";
import { DeleteTodoRequest } from "../dto/CreateTodoListFormData";



export class DeleteTodo extends Command<string, DeleteTodoRequest> {
     
    public constructor(private commandHandler: TodoCommandHandler){super()}

    public execute(request: DeleteTodoRequest): Observable<Result<string>> {
        return this.commandHandler.deleteTodo(request)
        .pipe(map(this.onSuccess),
         catchError(this.onError))
    }
}