import { Command } from "src/core/shared/command/Command";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/shared/dto/Result";
import { CreateTodoFormData, CreateTodoRequest } from "../dto/CreateTodoFormData";
import { IDProvider } from "src/core/shared/interfaces/IDProvider";


export class AddTodo extends Command {
    

    public constructor(private commandHandler: TodoCommandHandler,
                       private idProvider: IDProvider){super()}

    public execute(formData: CreateTodoFormData): Observable<Result> {
        const request: CreateTodoRequest = {...formData, id: this.idProvider.generate()}
        return this.commandHandler.addTodo(request)
        .pipe(map(this.onSuccess), 
         catchError(this.onError))
    }
}