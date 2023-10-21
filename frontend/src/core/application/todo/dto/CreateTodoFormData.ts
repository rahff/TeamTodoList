

export type CreateTodoFormData = {
    description: string,
    deadline: Date,
    ref: string
}

export type CreateTodoRequest = {
    description: string,
    deadline: Date,
    ref: string,
    id: string
}