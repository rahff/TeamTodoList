import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { Team } from "src/core/application/team/dto/Team";
import { TeamListView } from "./TeamListState";


const setTeamListPrepareAction: PrepareAction<TeamListView> = (payload: TeamListView) =>({payload});
export const teamListReceivedEvent = createAction("teamList/setTeamListView", setTeamListPrepareAction);

const addTeamPrepareAction: PrepareAction<Team> = (payload:  Team) =>({payload});
export const teamAddedEvent = createAction("teamList/addTeam", addTeamPrepareAction);

const removeTeammatePrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teammateRemovedFromTeamEvent = createAction("teamList/removeTeammate", removeTeammatePrepareAction);

const deleteTeamPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teamDeletedEvent = createAction("teamList/deleteTeam", deleteTeamPrepareAction);