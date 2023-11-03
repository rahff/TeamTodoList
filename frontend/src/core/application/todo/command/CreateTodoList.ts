

import { CreateTodoListFormData, CreateTodoListRequest } from "../dto/CreateTodoListFormData";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { Observable, catchError, map, of } from "rxjs";
import { Command } from "src/core/application/shared/command/Command";
import { TodoList } from "../../../model/todo/TodoList";
import { Result } from "../../shared/dto/Result";
import { DateProvider } from "../../shared/interfaces/DateProvider";
import { IDProvider } from "../../shared/interfaces/IDProvider";



export class CreateTodoList extends Command<TodoList, CreateTodoListFormData> {

    public constructor(private commandHandler: TodoCommandHandler, 
                       private idProvider: IDProvider,
                       private dateProvider: DateProvider){ super()}


    public execute(formData: CreateTodoListFormData): Observable<Result<TodoList>> {
        const request = this.createRequest(formData);
        return this.commandHandler.createTodoList(request)
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