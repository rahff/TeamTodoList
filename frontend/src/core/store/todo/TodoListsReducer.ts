
import { ActionWithPayload } from "src/core/shared/store/Actions";
import { TodoListsState } from "./TodoListsState";
import { TodoList } from "src/core/todo/dto/TodoList";
import { Todo } from "src/core/todo/dto/Todo";
import { TodoListDetails } from "src/core/todo/dto/TodoListDetails";


export const setTeamTodoLists = (state: TodoListsState, action: ActionWithPayload<TodoList[]>): TodoListsState => {
    return {
        ...state,
        teamLists: action.payload
    };
}


export const deleteTeamTodoList = (state: TodoListsState, action: ActionWithPayload<string>): TodoListsState => {
    return {
        ...state,
        teamLists: state.teamLists.filter((todoList: TodoList) => todoList.id !== action.payload)
    };
}


export const addTodo = (state: TodoListsState, action: ActionWithPayload<Todo>): TodoListsState => {
    if(!state.details) throw new Error("details not loaded");
    const mergeTodos = (todos: Todo[]): Todo[] => {
        return todos.concat(action.payload)
    }
    return {
        ...state,
        details: {
            ...state.details,
            todos: mergeTodos(state.details.todos)
        }
    };
    
}

export const addTeamTodoList = (state: TodoListsState, action: ActionWithPayload<TodoList>): TodoListsState => {
    return {
        ...state,
        teamLists: state.teamLists.concat(action.payload)
    }
}

export const addPersonnalTodoList = (state: TodoListsState, action: ActionWithPayload<TodoList>): TodoListsState => {
    return {
        ...state,
        personnalLists: state.personnalLists.concat(action.payload)
    }
}


