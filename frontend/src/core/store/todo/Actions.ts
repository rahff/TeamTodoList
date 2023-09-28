import { PrepareAction, createAction } from "@reduxjs/toolkit"
import { Todo } from "src/core/todo/dto/Todo";
import { TodoList } from "src/core/todo/dto/TodoList"


const deleteTeamTodoListsPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const deleteTeamTodoListAction = createAction("todoLists/deleteTeamTodoList", deleteTeamTodoListsPrepareAction);

const setTeamTodoListsPrepareAction: PrepareAction<TodoList[]> = (payload:  TodoList[]) =>({payload});
export const setTeamTodoListsAction = createAction("todoLists/setTeamTodoLists", setTeamTodoListsPrepareAction);

const addTodoPrepareAction: PrepareAction<Todo> = (payload:  Todo) =>({payload});
export const addTodoAction = createAction("todoLists/addTodo", addTodoPrepareAction);

const addTeamTodoListPrepareAction: PrepareAction<TodoList> = (payload:  TodoList) =>({payload});
export const addTeamTodoListAction = createAction("todoLists/addTeamTodoList", addTeamTodoListPrepareAction);

const addPersonnalTodoListPrepareAction: PrepareAction<TodoList> = (payload:  TodoList) =>({payload});
export const addPersonnalTodoListAction = createAction("todoLists/addPersonnalTodoList", addPersonnalTodoListPrepareAction);
