import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { BehaviorSubject, Observable } from "rxjs";
import { Teammate } from "src/core/application/team/dto/Teammate";
import { GlobalState } from "../Store";
import { StoreApi } from "../shared/StoreApi";



export class TeammateListStoreApi extends StoreApi {

    private teammateList = new BehaviorSubject<Teammate[]>([]);

    public constructor(store: ToolkitStore<GlobalState>){
        super(store)
    }

    protected emitNewState(): void {
        this.teammateList.next(this.store.getState().teammateList.list);
    }

    public getTeammateList(): Observable<Teammate[]> {
        return this.teammateList.asObservable();
    }    
}