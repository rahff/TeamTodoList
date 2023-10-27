import { BehaviorSubject, Observable, map } from "rxjs";
import { TodoList } from "src/core/application/todo/dto/TodoList";
import { StoreApi } from "../shared/StoreApi";
import { Store } from "../Store";
import { TodoListsViewModel, todoListsInitialState } from "./TodoListsState";




export class TodoListsStoreApi extends StoreApi {
    
    private ListOfTodoList = new BehaviorSubject<TodoListsViewModel>(todoListsInitialState);

    public constructor(store: Store){ super(store) }

    protected emitNewState(): void {
        this.ListOfTodoList.next(this.store.getState().todoLists);
    }

    public getListOfTodoList(): Observable<TodoList[]> {
        return this.ListOfTodoList.asObservable()
        .pipe(map((view) => view.viewModel.lists));
    }
}

