import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { TodoListDetailsViewModel } from "./TodoListDetailsState";
import { Todo } from "src/core/model/todo/Todo";




const setTodoListDetailsPrepareAction: PrepareAction<TodoListDetailsViewModel> = (payload: TodoListDetailsViewModel) =>({payload});
export const todoListDetailsReceivedEvent = createAction("todoListDetails/setTodoListDetails", setTodoListDetailsPrepareAction);

const addTodoPrepareAction: PrepareAction<Todo> = (payload: Todo) =>({payload});
export const todoAddedEvent = createAction("todoListDetails/addTodo", addTodoPrepareAction);


const deleteTodoPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const todoDeletedEvent = createAction("todoListDetails/deleteTodo", deleteTodoPrepareAction);

const doneTodoPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const todoDonedEvent = createAction("todoListDetails/doneTodo", doneTodoPrepareAction);

