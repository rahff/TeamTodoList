import {DATA_SOURCE} from "../../../environments/constants";
import {BrowserContextService} from "../../dashboard/services/shared/browser-context.service";
import {TokenAuthentication} from "../../authentication/services/TokenAuthentication.service";
import {InMemoryTokenAuthentication} from "../../authentication/services/in-memory-token-authentication.service";

export const AuthenticationByTokenDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTokenAuthentication, BrowserContextService] :
        [TokenAuthentication, BrowserContextService]
}
