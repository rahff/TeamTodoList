import { Todo } from "src/core/model/todo/Todo"





export const toggleTodo = (todo: Todo) => {
    return {
        ...todo,
        done: !todo.done
    }
}
