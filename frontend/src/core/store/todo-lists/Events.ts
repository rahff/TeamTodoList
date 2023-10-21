import { PrepareAction, createAction } from "@reduxjs/toolkit"
import { Todo } from "src/core/application/todo/dto/Todo";
import { TodoList } from "src/core/application/todo/dto/TodoList";
import { TodoListsView } from "./TodoListsState";



const setPersonalTodoListsPrepareAction: PrepareAction<TodoListsView> = (payload: TodoListsView) =>({payload});
export const todoListsReceivedEvent = createAction("todoLists/setTodoLists", setPersonalTodoListsPrepareAction);

const deleteTodoListsPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const todoListDeletedEvent = createAction("todoLists/deleteTodoList", deleteTodoListsPrepareAction);


const addTodoListPrepareAction: PrepareAction<TodoList> = (payload:  TodoList) =>({payload});
export const todoListAddedEvent = createAction("todoLists/addTodoList", addTodoListPrepareAction);

