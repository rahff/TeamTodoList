export type CreateTodoListFormData = {
    name: string,
    ref: string
}

export type CreateTodoListRequest = {
    name: string,
    id: string,
    createdAt: Date,
    ref: string
}

export type DeleteTodoRequest = {
    todoId: string,
    todoListId: string
}