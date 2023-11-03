import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { TeamDetailsViewModel } from "./TeamDetailsState";
import { TodoList } from "src/core/model/todo/TodoList";
import { Teammate } from "src/core/model/team/Teammate";




const deleteTeamTodoListPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teamTodoListDeletedEvent = createAction("teamDetails/deleteTodoList", deleteTeamTodoListPrepareAction);

const setTeamDetailsPrepareAction: PrepareAction<TeamDetailsViewModel> = (payload: TeamDetailsViewModel) =>({payload});
export const teamDetailsReceivedEvent = createAction("teamDetails/setTeamDetails", setTeamDetailsPrepareAction);

const addTeamTodoListPrepareAction: PrepareAction<TodoList> = (payload:  TodoList) =>({payload});
export const teamTodoListAddedEvent = createAction("teamDetails/addTodoList", addTeamTodoListPrepareAction);

const addTeammatesOnTeamPrepareAction: PrepareAction<Teammate[]> = (payload:  Teammate[]) =>({payload});
export const teammatesOnTeamAddedEvent = createAction("teamDetails/addTeammatesOnTeam", addTeammatesOnTeamPrepareAction);

const removeTeammateFromTeamPrepareAction: PrepareAction<string> = (payload: string) =>({payload});
export const teammateRemovedFromTeamEvent = createAction("teamDetails/removeTeammateFromTeam", removeTeammateFromTeamPrepareAction);

