import {DATA_SOURCE} from "../../../../environments/constants";

import {InMemoryAccountQueryHandler} from "../../services/inMemory/InMemoryAccountQueryHandler";
import {AccountQueryService} from "../../services/account-query.service";
import {BrowserContextService} from "../../services/browser-context.service";


export const UserAccountDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryAccountQueryHandler, BrowserContextService] :
        [AccountQueryService, BrowserContextService]
}

