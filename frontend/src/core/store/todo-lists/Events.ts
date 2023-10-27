import { PrepareAction, createAction } from "@reduxjs/toolkit"
import { Todo } from "src/core/application/todo/dto/Todo";
import { TodoList } from "src/core/application/todo/dto/TodoList";
import { TodoListsViewModel } from "./TodoListsState";



const setPersonalTodoListsPrepareAction: PrepareAction<TodoListsViewModel> = (payload: TodoListsViewModel) =>({payload});
export const todoListsReceivedEvent = createAction("todoLists/setTodoLists", setPersonalTodoListsPrepareAction);

const deleteTodoListsPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const todoListDeletedEvent = createAction("todoLists/deleteTodoList", deleteTodoListsPrepareAction);


const addTodoListPrepareAction: PrepareAction<TodoList> = (payload:  TodoList) =>({payload});
export const todoListAddedEvent = createAction("todoLists/addTodoList", addTodoListPrepareAction);

