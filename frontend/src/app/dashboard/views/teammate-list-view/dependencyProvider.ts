import {DATA_SOURCE} from "../../../../environments/constants";
import {InMemoryTeamQueryHandler} from "../../services/inMemory/InMemoryTeamQueryHandler";
import {TeamQueryService} from "../../services/team/team-query.service";
import {BrowserContextService} from "../../services/shared/browser-context.service";
import {InMemoryTeamCommandHandler} from "../../services/inMemory/InMemoryTeamCommandHandler";
import {UUIDService} from "../../services/shared/uuid.service";
import {TeamCommandService} from "../../services/team/team-command.service";


export const TeammateListDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTeamQueryHandler, BrowserContextService] :
        [TeamQueryService, BrowserContextService]
}
export const JoinTeammateDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTeamCommandHandler, UUIDService] :
        [TeamCommandService, UUIDService]
}