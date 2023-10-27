import { Observable, of, throwError } from "rxjs";
import { CreateTodoListRequest } from "../../../../core/application/todo/dto/CreateTodoListFormData";
import { TodoCommandHandler } from "../../../../core/application/todo/spi/TodoCommandHandler";
import { TodoList } from "../../../../core/application/todo/dto/TodoList";
import { CreateTodoRequest } from "../../../../core/application/todo/dto/CreateTodoFormData";
import { Todo } from "../../../../core/application/todo/dto/Todo";
import { newTeamTodoList, newTodo } from "../../../../core/application/todo/in-memory/data/data.todo";
import { Injectable } from "@angular/core";




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

    public doneTodo(todoId: string): Observable<string> {
        this.methodCalls.set("doneTodo", todoId);
        if(todoId === "failure") return throwError(() => new Error("bad request"));
        return of(todoId);
    }

    public deleteTodo(todoId: string): Observable<string> {
        this.methodCalls.set("deleteTodo", todoId)
        if(todoId == "failure") return throwError(() => new Error("bad request"));
        return of(todoId);
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