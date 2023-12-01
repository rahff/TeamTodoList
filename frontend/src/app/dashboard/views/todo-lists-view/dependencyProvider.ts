import {InMemoryTodoQueryHandler} from "../../services/inMemory/InMemoryTodoQueryHandler";
import {DATA_SOURCE} from "../../../../environments/constants";
import {TodoQueryService} from "../../services/todo/todo-query.service";
import {BrowserContextService} from "../../services/shared/browser-context.service";




export const dependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTodoQueryHandler, BrowserContextService] :
        [TodoQueryService, BrowserContextService]

}