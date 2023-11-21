import {DATA_SOURCE} from "../../../../environments/constants";
import {InMemoryTodoQueryHandler} from "../../services/inMemory/InMemoryTodoQueryHandler";
import {TodoQueryService} from "../../services/todo-query.service";
import {UUIDService} from "../../services/uuid.service";
import {InMemoryTodoCommandHandler} from "../../services/inMemory/InMemoryTodoCommandHandler";
import {TodoCommandService} from "../../services/todo-command.service";


export const TodoListByIdDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTodoQueryHandler] :
        [TodoQueryService]
}
export const AddTodoDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTodoCommandHandler, UUIDService] :
        [TodoCommandService, UUIDService]
}
export const DoneTodoDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTodoCommandHandler] :
        [TodoCommandService]
}
export const DeleteTodoDependencyProvider = (dataSource: number) => {
    return dataSource === DATA_SOURCE.IN_MEMORY ?
        [InMemoryTodoCommandHandler] :
        [TodoCommandService]
}