import { BehaviorSubject, Observable, map } from "rxjs";
import { StoreApi } from "../shared/StoreApi";
import { TodoListDetails, TodoListDetailsViewModel, todoListDetailsInitialState } from "./TodoListDetailsState";
import { Store } from "../Store";




export class TodoListDetailsStoreApi extends StoreApi {

    private todoListDetails = new BehaviorSubject<TodoListDetailsViewModel>(todoListDetailsInitialState);

    public constructor(store: Store){super(store)}

    protected emitNewState(): void {
        this.todoListDetails.next(this.store.getState().todoListDetails);
    }

    public getTodoListDetails(): Observable<TodoListDetails> {
        return this.todoListDetails.asObservable()
        .pipe(map((view) => view.viewModel));
    }
}