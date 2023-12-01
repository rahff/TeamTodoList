import { Observable, of, throwError } from "rxjs";
import { Injectable } from "@angular/core";
import { CreateTodoRequest } from "src/core/application/todo/dto/CreateTodoFormData";
import { CreateTodoListRequest, DeleteTodoRequest } from "src/core/application/todo/dto/CreateTodoListFormData";
import { newTeamTodoList, newTodo } from "src/core/application/todo/in-memory/data/data.todo";
import { TodoCommandHandler } from "src/core/application/todo/spi/TodoCommandHandler";
import { Todo } from "src/core/model/todo/Todo";
import { TodoList } from "src/core/model/todo/TodoList";
import {DoneTodoRequest} from "../../../../core/application/todo/dto/DoneTodoRequest";




@Injectable({
    providedIn: "root"
})
export class InMemoryTodoCommandHandler implements TodoCommandHandler {
    
    private methodCalls: Map<TodoDataSourceMethods, Object>;

    constructor(){
        this.methodCalls = new Map<TodoDataSourceMethods, Object>();
    }

    public createTodoList(request: CreateTodoListRequest): Observable<TodoList> {
        this.methodCalls.set("createTodo", request);
        if(request.name === "will fail"){
            return throwError(() => new Error("bad request"));
        }
        return of(newTeamTodoList);
    }

    public getTodoListsByRef(ref: string): Observable<TodoList[]> {
        throw new Error("Method not implemented.");
    }

    public addTodo(request: CreateTodoRequest): Observable<Todo> {
        this.methodCalls.set("addTodo", request);
        if(request.description === "will fail"){
            return throwError(() => new Error("bad request"))
        }
        return of(newTodo);
    }

    public doneTodo(request: DoneTodoRequest): Observable<string> {
        this.methodCalls.set("doneTodo", request);
        if(request.todoId === "failure") return throwError(() => new Error("bad request"));
        return of(request.todoId);
    }

    public deleteTodo(request: DeleteTodoRequest): Observable<string> {
        this.methodCalls.set("deleteTodo", request)
        if(request.todoId == "failure") return throwError(() => new Error("bad request"));
        return of(request.todoId);
    }

    public deleteTodoList(todoListId: string): Observable<string> {
        this.methodCalls.set("deleteTodoListTodo", todoListId)
        if(todoListId == "failure") return throwError(() => new Error("bad request"));
        return of(todoListId);
    }

    public hasBeenCalled(methodName: TodoDataSourceMethods): any {
        return this.methodCalls.get(methodName)
    }

}

type TodoDataSourceMethods = "createTodo" | "addTodo" | "deleteTodo" | "doneTodo" | "deleteTodoListTodo";