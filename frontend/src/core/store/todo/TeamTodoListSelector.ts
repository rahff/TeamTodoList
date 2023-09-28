import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { GlobalState } from "../Store";
import { BehaviorSubject, Observable } from "rxjs";
import { TodoList } from "src/core/todo/dto/TodoList";
import { TodoListDetails } from "src/core/todo/dto/TodoListDetails";



export class TeamTodoListsSelector {

    private unSubscribe: Function;
    private ListOfTodoList = new BehaviorSubject<TodoList[]>([])

    public constructor(private store: ToolkitStore<GlobalState>){
        this.unSubscribe = this.store.subscribe(() => this.emitNewState())
    }

    private emitNewState(): void {
        this.ListOfTodoList.next(this.store.getState().todoLists.teamLists);
    }

    public getListOfTodoList(): Observable<TodoList[]> {
        return this.ListOfTodoList.asObservable();
    }

    public removeStoreSubscription(): void {
        this.unSubscribe();
    }
}

export class PersonnalTodoListsSelector {

    private unSubscribe: Function;
    private ListOfTodoList = new BehaviorSubject<TodoList[]>([])

    public constructor(private store: ToolkitStore<GlobalState>){
        this.unSubscribe = this.store.subscribe(() => this.emitNewState())
    }

    private emitNewState(): void {
        this.ListOfTodoList.next(this.store.getState().todoLists.personnalLists);
    }

    public getListOfTodoList(): Observable<TodoList[]> {
        return this.ListOfTodoList.asObservable();
    }

    public removeStoreSubscription(): void {
        this.unSubscribe();
    }
}

export class TodoListDetailsSelector {

    private unSubscribe: Function;
    private todoListDetails = new BehaviorSubject<TodoListDetails | null>(null)

    public constructor(private store: ToolkitStore<GlobalState>){
        this.unSubscribe = this.store.subscribe(() => this.emitNewState())
    }

    private emitNewState(): void {
        this.todoListDetails.next(this.store.getState().todoLists.details);
    }

    public getTodoListDetails(): Observable<TodoListDetails | null> {
        return this.todoListDetails.asObservable();
    }

    public removeStoreSubscription(): void {
        this.unSubscribe();
    }
}