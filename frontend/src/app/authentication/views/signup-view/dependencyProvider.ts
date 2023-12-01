import {DATA_SOURCE} from "../../../../environments/constants";
import {InMemoryEmailPasswordAuthentication} from "../../services/in-memory-email-password-authentication.service";
import {EmailPasswordAuthentication} from "../../services/EmailPasswordAuthentication.service";
import {BrowserContextService} from "../../../dashboard/services/shared/browser-context.service";


export const signupUserDependencyProvider = (dataSource: number) => {
     return dataSource === DATA_SOURCE.IN_MEMORY ?
         [InMemoryEmailPasswordAuthentication, BrowserContextService] :
         [EmailPasswordAuthentication, BrowserContextService]

}