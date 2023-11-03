import { BehaviorSubject, Observable, map } from "rxjs";
import { Store } from "../Store";
import { StoreApi } from "../shared/StoreApi";
import { TeamDetailsViewModel, teamDetailsInitialState } from "./TeamDetailsState";
import { Team } from "src/core/model/team/Team";
import { Teammate } from "src/core/model/team/Teammate";



export class TeamDetailsStoreApi extends StoreApi {

    private teamDetails = new BehaviorSubject<TeamDetailsViewModel>(teamDetailsInitialState);

    public constructor(store: Store){
        super(store)
    }

    protected emitNewState(): void {
        this.teamDetails.next(this.store.getState().teamDetails);
       
    }

    public getTeamDetails(): Observable<Team | null> {
        return this.teamDetails.asObservable()
        .pipe(map((view) => view.viewModel.details));
    }

    public getAvailableTeammates(): Observable<Teammate[]> {
        return this.teamDetails.asObservable()
        .pipe(map((view) => view.viewModel.availableTeammates));
    }
}