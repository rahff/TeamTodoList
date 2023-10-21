import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { BehaviorSubject, Observable, map } from "rxjs";
import { GlobalState } from "../Store";
import { Team } from "src/core/application/team/dto/Team";
import { StoreApi } from "../shared/StoreApi";
import { Teammate } from "src/core/application/team/dto/Teammate";
import { TodoList } from "src/core/application/todo/dto/TodoList";




export class TeamListStoreApi extends StoreApi {

    private teamList = new BehaviorSubject<Team[]>([]);

    public constructor(store: ToolkitStore<GlobalState>){
        super(store)
    }

    protected emitNewState(): void {
        this.teamList.next(this.store.getState().teamList.list);
    }

    public getTeamList(): Observable<Team[]> {
        return this.teamList.asObservable();
    }
  
}


