import { TodoList } from "src/core/todo/dto/TodoList"
import { Teammate } from "./Teammate"

export type Team = {
    name: string,
    teammates: Teammate[],
    id: string,
    todoLists: TodoList[]
}