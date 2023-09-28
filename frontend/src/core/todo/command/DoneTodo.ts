import { Command } from "src/core/shared/command/Command";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/shared/dto/Result";

export class DoneTodo extends Command {

    public constructor(private commandHandler: TodoCommandHandler){super()}

    public execute(todoId: string): Observable<Result> {
        return this.commandHandler.doneTodo(todoId)
        .pipe(map(this.onSuccess),
         catchError(this.onError))
    }
}