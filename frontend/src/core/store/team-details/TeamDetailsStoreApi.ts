import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { BehaviorSubject, Observable } from "rxjs";
import { Team } from "src/core/application/team/dto/Team";
import { TodoList } from "src/core/application/todo/dto/TodoList";
import { GlobalState } from "../Store";
import { StoreApi } from "../shared/StoreApi";



export class TeamDetailsStoreApi extends StoreApi {

    private teamDetails = new BehaviorSubject<Team | null>(null);

    public constructor(store: ToolkitStore<GlobalState>){
        super(store)
    }

    protected emitNewState(): void {
        this.teamDetails.next(this.store.getState().teamDetails.details);
       
    }

    public getTeamDetails(): Observable<Team | null> {
        return this.teamDetails.asObservable();
    }
}