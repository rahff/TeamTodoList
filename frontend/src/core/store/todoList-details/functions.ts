import { Todo } from "src/core/application/todo/dto/Todo"



export const toggleTodo = (todo: Todo) => {
    return {
        ...todo,
        done: !todo.done
    }
}
