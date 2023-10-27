import { BehaviorSubject, Observable, map } from "rxjs";
import { Store } from "../Store";
import { Team } from "src/core/application/team/dto/Team";
import { StoreApi } from "../shared/StoreApi";
import { TeamListViewModel, teamListInitialState } from "./TeamListState";



export class TeamListStoreApi extends StoreApi {

    private teamList = new BehaviorSubject<TeamListViewModel>(teamListInitialState);

    public constructor(store: Store){
        super(store)
    }

    protected emitNewState(): void {
        this.teamList.next(this.store.getState().teamList);
    }

    public getTeamList(): Observable<Team[]> {
        return this.teamList.asObservable()
        .pipe(map((view) => view.viewModel.list));
    }
  
}


