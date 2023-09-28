import { Command } from "src/core/shared/command/Command";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/shared/dto/Result";

export class DeleteTodoList extends Command {

    public constructor(private commandHandler: TodoCommandHandler){super()}

    public execute(todoListTodeleteId: string): Observable<Result> {
        return this.commandHandler.deleteTodoListTodo(todoListTodeleteId)
        .pipe(map(this.onSuccess),
         catchError(this.onError))
    }
}