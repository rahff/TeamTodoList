import { PrepareAction, createAction } from "@reduxjs/toolkit";

import { TodoList } from "src/core/application/todo/dto/TodoList";
import { TeamDetailsView } from "./TeamDetailsState";



const deleteTeamTodoListPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teamTodoListDeletedEvent = createAction("teamDetails/deleteTodoList", deleteTeamTodoListPrepareAction);

const setTeamDetailsPrepareAction: PrepareAction<TeamDetailsView> = (payload: TeamDetailsView) =>({payload});
export const teamDetailsReceivedEvent = createAction("teamDetails/setTeamDetails", setTeamDetailsPrepareAction);

const addTeamTodoListPrepareAction: PrepareAction<TodoList> = (payload:  TodoList) =>({payload});
export const teamTodoListAddedEvent = createAction("teamDetails/addTodoList", addTeamTodoListPrepareAction);

const removeTeammateFromTeamPrepareAction: PrepareAction<string> = (payload: string) =>({payload});
export const teammateRemovedFromTeamEvent = createAction("teamDetails/removeTeammateFromTeam", removeTeammateFromTeamPrepareAction);

