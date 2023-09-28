import { Observable } from "rxjs";
import { Team } from "../dto/Team";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";



export class TeamById {

    public constructor(private queryHandler: TeamQueryHandler){}

    public query(teamId: string): Observable<Team> {
        return this.queryHandler.getTeamById(teamId);
    }
}