

export type CreateTodoFormData = {
    description: string,
    deadline: string,
    todoListId: string
}

export type CreateTodoRequest = {
    description: string,
    deadline: string,
    todoListId: string,
    id: string
}