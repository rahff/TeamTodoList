import { Command } from "src/core/application/shared/command/Command";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";
import { CreateTodoFormData, CreateTodoRequest } from "../dto/CreateTodoFormData";
import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";
import { Todo } from "../dto/Todo";


export class AddTodo extends Command<Todo, CreateTodoFormData> {

    public constructor(private commandHandler: TodoCommandHandler,
                       private idProvider: IDProvider){super()}

    public execute(formData: CreateTodoFormData): Observable<Result<Todo>> {
        const request: CreateTodoRequest = {...formData, id: this.idProvider.generate()}
        return this.commandHandler.addTodo(request)
        .pipe(map(this.onSuccess), 
         catchError(this.onError))
    }
}