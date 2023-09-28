import { Observable } from "rxjs";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { Teammate } from "../dto/Teammate";

export class TeammateById {

    public constructor(private queryHandler: TeamQueryHandler){}

    public query(teammateId: string): Observable<Teammate> {
        return this.queryHandler.getTeammateById(teammateId);
    }
}