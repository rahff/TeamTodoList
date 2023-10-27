import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { Team } from "src/core/application/team/dto/Team";
import { TeamListViewModel } from "./TeamListState";


const setTeamListPrepareAction: PrepareAction<TeamListViewModel> = (payload: TeamListViewModel) =>({payload});
export const teamListReceivedEvent = createAction("teamList/setTeamListView", setTeamListPrepareAction);

const addTeamPrepareAction: PrepareAction<Team> = (payload:  Team) =>({payload});
export const teamCreatedEvent = createAction("teamList/addTeam", addTeamPrepareAction);

const removeTeammatePrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teammateRemovedFromTeamEvent = createAction("teamList/removeTeammate", removeTeammatePrepareAction);

const deleteTeamPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teamDeletedEvent = createAction("teamList/deleteTeam", deleteTeamPrepareAction);