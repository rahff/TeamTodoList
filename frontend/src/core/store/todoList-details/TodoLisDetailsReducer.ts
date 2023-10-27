import { Todo } from "src/core/application/todo/dto/Todo";
import { EventWithPayload } from "../shared/Event"
import { TodoListDetailsViewModel } from "./TodoListDetailsState"
import { addItemOnList, deleteById, filterList, mapOnList, updateById } from "../shared/functions";
import { toggleTodo } from "./functions";



type TodoListDetailsReducer<T> = (state: TodoListDetailsViewModel, event: EventWithPayload<T>) => TodoListDetailsViewModel;


export const setTodoListDetails: TodoListDetailsReducer<TodoListDetailsViewModel> = (_, event) => {
    return event.payload;
}

export const doneTodo: TodoListDetailsReducer<string> = (state, event) => {
    const todos = state.viewModel.todos;
    const todoId = event.payload;
    return {
        viewModel: {
            ...state.viewModel,
            todos: mapOnList(todos, updateById)(toggleTodo, todoId)
        }
    };
}

export const deleteTodo: TodoListDetailsReducer<string> = (state, event) => {
    const todos = state.viewModel.todos;
    const todoId = event.payload;
    return {
        viewModel: {
            ...state.viewModel,
            todos: filterList(todos, deleteById)(todoId)
        }
    }
}



export const addTodo: TodoListDetailsReducer<Todo> = (state, event) => {
    const todos = state.viewModel.todos;
    const newTodo = event.payload;
    return {
        viewModel: {
            ...state.viewModel,
            todos: addItemOnList(todos)(newTodo)
        }
    }
}

