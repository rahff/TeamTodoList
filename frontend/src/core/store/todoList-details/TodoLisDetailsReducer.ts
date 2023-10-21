import { Todo } from "src/core/application/todo/dto/Todo";
import { EventWithPayload } from "../shared/Event"
import { TodoListDetailsView } from "./TodoListDetailsState"
import { addItemOnList, deleteById, filterList, mapOnList, updateById } from "../shared/functions";
import { toggleTodo } from "./functions";



type TodoListDetailsReducer<T> = (state: TodoListDetailsView, event: EventWithPayload<T>) => TodoListDetailsView;


export const setTodoListDetails: TodoListDetailsReducer<TodoListDetailsView> = (_, event) => {
    return event.payload;
}

export const doneTodo: TodoListDetailsReducer<string> = (state, event) => {
    const todos = state.details.todos;
    const todoId = event.payload;
    return {
        details: {
            ...state.details,
            todos: mapOnList(todos, updateById)(toggleTodo, todoId)
        }
    };
}

export const deleteTodo: TodoListDetailsReducer<string> = (state, event) => {
    const todos = state.details.todos;
    const todoId = event.payload;
    return {
        details: {
            ...state.details,
            todos: filterList(todos, deleteById)(todoId)
        }
    }
}



export const addTodo: TodoListDetailsReducer<Todo> = (state, event) => {
    const todos = state.details.todos;
    const newTodo = event.payload;
    return {
        details: {
            ...state.details,
            todos: addItemOnList(todos)(newTodo)
        }
    }
}

