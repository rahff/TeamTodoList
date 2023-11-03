import { TodoList } from "../todo/TodoList"
import { Teammate } from "./Teammate"


export type Team = {
    name: string,
    teammates: Teammate[],
    id: string,
    todoLists: TodoList[]
}