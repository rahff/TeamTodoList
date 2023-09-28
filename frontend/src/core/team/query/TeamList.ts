import { Observable } from "rxjs";
import { Team } from "../dto/Team";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { UserContextHolder } from "src/core/shared/interfaces/UserContextHolder";

export class TeamList {

    public constructor(private queryHandler: TeamQueryHandler, private userContextHolder: UserContextHolder){}

    public query(): Observable<Team[]> {
        const accountId = this.userContextHolder.getAccountId();
        return this.queryHandler.getTeamList(accountId);
    }
}