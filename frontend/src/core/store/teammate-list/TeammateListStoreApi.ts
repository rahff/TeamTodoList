import { BehaviorSubject, Observable, map } from "rxjs";
import { Teammate } from "src/core/application/team/dto/Teammate";
import { Store } from "../Store";
import { StoreApi } from "../shared/StoreApi";
import { TeammateListViewModel, teammateListInitialState } from "./TeammateListState";



export class TeammateListStoreApi extends StoreApi {

    private teammateList = new BehaviorSubject<TeammateListViewModel>(teammateListInitialState);

    public constructor(store: Store){
        super(store)
    }

    protected emitNewState(): void {
        this.teammateList.next(this.store.getState().teammateList);
    }

    public getTeammateList(): Observable<Teammate[]> {
        return this.teammateList.asObservable()
        .pipe(map((view)=> view.viewModel.list));
    }    
}