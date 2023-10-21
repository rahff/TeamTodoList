import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { BehaviorSubject, Observable } from "rxjs";
import { GlobalState } from "../Store";
import { StoreApi } from "../shared/StoreApi";
import { TodoListDetails } from "./TodoListDetailsState";




export class TodoListDetailsStoreApi extends StoreApi {

   
    private todoListDetails = new BehaviorSubject<TodoListDetails>({info: null, todos: []});

    public constructor(store: ToolkitStore<GlobalState>){super(store)}

    protected emitNewState(): void {
        this.todoListDetails.next(this.store.getState().todoListDetails.details);
    }

    public getTodoListDetails(): Observable<TodoListDetails> {
        return this.todoListDetails.asObservable();
    }
}