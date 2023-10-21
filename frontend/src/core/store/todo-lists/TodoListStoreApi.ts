import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { BehaviorSubject, Observable } from "rxjs";
import { GlobalState } from "../Store";
import { TodoList } from "src/core/application/todo/dto/TodoList";
import { StoreApi } from "../shared/StoreApi";





export class TodoListsStoreApi extends StoreApi {
    
    private ListOfTodoList = new BehaviorSubject<TodoList[]>([]);

    public constructor(store: ToolkitStore<GlobalState>){ super(store) }

    protected emitNewState(): void {
        this.ListOfTodoList.next(this.store.getState().todoLists.lists);
    }

    public getListOfTodoList(): Observable<TodoList[]> {
        return this.ListOfTodoList.asObservable();
    }
}

