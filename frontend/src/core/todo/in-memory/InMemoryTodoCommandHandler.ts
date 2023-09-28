import { Observable, of, throwError } from "rxjs";
import { CreateTodoListRequest } from "../dto/CreateTodoListFormData";
import { TodoCommandHandler } from "../spi/TodoCommandHandler";
import { TodoList } from "../dto/TodoList";
import { CreateTodoRequest } from "../dto/CreateTodoFormData";



export class InMemoryTodoCommandHandler implements TodoCommandHandler {
    
    private methodCalls: Map<TodoDataSourceMethods, Object>;

    constructor(){
        this.methodCalls = new Map<TodoDataSourceMethods, Object>();
    }

    public createTodo(request: CreateTodoListRequest): Observable<true> {
        this.methodCalls.set("createTodo", request);
        if(request.name === "will fail"){
            return throwError(() => new Error("bad request"));
        }
        return of(true);
    }

    public getTodoListsByRef(ref: string): Observable<TodoList[]> {
        throw new Error("Method not implemented.");
    }

    public addTodo(request: CreateTodoRequest): Observable<true> {
        this.methodCalls.set("addTodo", request);
        if(request.description === "will fail"){
            return throwError(() => new Error("bad request"))
        }
        return of(true);
    }

    public doneTodo(todoId: string): Observable<true> {
        this.methodCalls.set("doneTodo", todoId);
        if(todoId === "failure") return throwError(() => new Error("bad request"));
        return of(true);
    }

    public deleteTodo(todoId: string): Observable<true> {
        this.methodCalls.set("deleteTodo", todoId)
        if(todoId == "failure") return throwError(() => new Error("bad request"));
        return of(true);
    }

    public deleteTodoListTodo(todoListId: string): Observable<true> {
        this.methodCalls.set("deleteTodoListTodo", todoListId)
        if(todoListId == "failure") return throwError(() => new Error("bad request"));
        return of(true);
    }

    public hasBeenCalled(methodName: TodoDataSourceMethods): any {
        return this.methodCalls.get(methodName)
    }

}

type TodoDataSourceMethods = "createTodo" | "addTodo" | "deleteTodo" | "doneTodo" | "deleteTodoListTodo";