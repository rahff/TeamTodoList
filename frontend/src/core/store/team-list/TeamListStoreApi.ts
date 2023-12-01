import { BehaviorSubject, Observable, map } from "rxjs";
import { Store } from "../Store";
import { StoreApi } from "../shared/StoreApi";
import { TeamListViewModel, teamListInitialState } from "./TeamListState";
import { TeamCard } from "src/core/model/team/TeamCard";
import {Teammate} from "../../model/team/Teammate";



export class TeamListStoreApi extends StoreApi {

    private teamList = new BehaviorSubject<TeamListViewModel>(teamListInitialState);

    public constructor(store: Store){
        super(store)
    }

    protected emitNewState(): void {
        this.teamList.next(this.store.getState().teamList);
    }

    public getTeamList(): Observable<TeamListViewModel> {
        return this.teamList.asObservable();
    }

  
}


