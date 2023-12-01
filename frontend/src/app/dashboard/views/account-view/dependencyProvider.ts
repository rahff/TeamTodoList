import {DATA_SOURCE} from "../../../../environments/constants";

import {InMemoryAccountQueryHandler} from "../../services/inMemory/InMemoryAccountQueryHandler";
import {AccountQueryService} from "../../services/account/account-query.service";
import {BrowserContextService} from "../../services/shared/browser-context.service";

import {InMemoryAccountCommandService} from "../../services/inMemory/inMemory-account-command.service";


export const UserAccountDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryAccountQueryHandler, BrowserContextService] :
        [AccountQueryService, BrowserContextService]
}
export const ChangePasswordDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryAccountCommandService, BrowserContextService] :
        [InMemoryAccountCommandService, BrowserContextService]
}

