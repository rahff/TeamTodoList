import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { TodoListDetailsView } from "./TodoListDetailsState";
import { Todo } from "src/core/application/todo/dto/Todo";


const setTodoListDetailsPrepareAction: PrepareAction<TodoListDetailsView> = (payload: TodoListDetailsView) =>({payload});
export const todoListDetailsReceivedEvent = createAction("todoListDetails/setTodoListDetails", setTodoListDetailsPrepareAction);

const addTodoPrepareAction: PrepareAction<Todo> = (payload: Todo) =>({payload});
export const todoAddedEvent = createAction("todoListDetails/addTodo", addTodoPrepareAction);


const deleteTodoPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const todoDeletedEvent = createAction("todoListDetails/deleteTodo", deleteTodoPrepareAction);

const doneTodoPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const todoDonedEvent = createAction("todoListDetails/doneTodo", doneTodoPrepareAction);

