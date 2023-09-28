import { Todo } from "./Todo"

export type TodoListDetails = {
    name: string,
    id: string,
    createdAt: string,
    todos: Todo[],
    complete: number
}