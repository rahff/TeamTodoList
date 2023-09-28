
import { CreateTodoListFormData, CreateTodoListRequest } from "../dto/CreateTodoListFormData";
import { Result } from "../../shared/dto/Result";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map, of } from "rxjs";
import { IDProvider } from "../../shared/interfaces/IDProvider";
import { DateProvider } from "../../shared/interfaces/DateProvider";
import { Command } from "src/core/shared/command/Command";



export class CreateTodoList extends Command {

    public constructor(private commandHandler: TodoCommandHandler, 
                       private idProvider: IDProvider,
                       private dateProvider: DateProvider){ super()}


    public execute(formData: CreateTodoListFormData): Observable<Result> {
        const request = this.createRequest(formData);
        return this.commandHandler.createTodo(request)
        .pipe(map(this.onSuccess), 
         catchError(this.onError));
    }

    private createRequest(formData: CreateTodoListFormData): CreateTodoListRequest {
        return {
            name: formData.name, 
            id: this.idProvider.generate(), 
            createdAt: this.dateProvider.now(), 
            ref: formData.ref
        };
    }
}