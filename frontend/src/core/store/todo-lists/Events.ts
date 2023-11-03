import { PrepareAction, createAction } from "@reduxjs/toolkit"
import { TodoListsViewModel } from "./TodoListsState";
import { TodoList } from "src/core/model/todo/TodoList";



const setPersonalTodoListsPrepareAction: PrepareAction<TodoListsViewModel> = (payload: TodoListsViewModel) =>({payload});
export const todoListsReceivedEvent = createAction("todoLists/setTodoLists", setPersonalTodoListsPrepareAction);

const deleteTodoListsPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const todoListDeletedEvent = createAction("todoLists/deleteTodoList", deleteTodoListsPrepareAction);


const addTodoListPrepareAction: PrepareAction<TodoList> = (payload:  TodoList) =>({payload});
export const todoListAddedEvent = createAction("todoLists/addTodoList", addTodoListPrepareAction);

