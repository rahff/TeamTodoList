import { Command } from "src/core/application/shared/command/Command";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";

export class DoneTodo extends Command<string, string> {

    public constructor(private commandHandler: TodoCommandHandler){super()}

    public execute(todoId: string): Observable<Result<string>> {
        return this.commandHandler.doneTodo(todoId)
        .pipe(map(this.onSuccess),
         catchError(this.onError))
    }
}