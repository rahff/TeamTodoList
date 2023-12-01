import {DATA_SOURCE} from "../../../../environments/constants";
import {InMemoryTeamQueryHandler} from "../../services/inMemory/InMemoryTeamQueryHandler";
import {TeamQueryService} from "../../services/team/team-query.service";
import {InMemoryTeamCommandHandler} from "../../services/inMemory/InMemoryTeamCommandHandler";
import {TeamCommandService} from "../../services/team/team-command.service";


export const TeamByIdDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTeamQueryHandler] :
        [TeamQueryService]
}

export const AddTeammateOnTeamDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTeamCommandHandler] :
        [TeamCommandService]
}
