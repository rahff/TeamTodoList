import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { TeamListViewModel } from "./TeamListState";
import { Team } from "src/core/model/team/Team";
import { TeamCard } from "src/core/model/team/TeamCard";


const setTeamListPrepareAction: PrepareAction<TeamListViewModel> = (payload: TeamListViewModel) =>({payload});
export const teamListReceivedEvent = createAction("teamList/setTeamListView", setTeamListPrepareAction);

const addTeamPrepareAction: PrepareAction<TeamCard> = (payload:  TeamCard) =>({payload});
export const teamCreatedEvent = createAction("teamList/addTeam", addTeamPrepareAction);

const removeTeammatePrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teammateRemovedFromTeamEvent = createAction("teamList/removeTeammate", removeTeammatePrepareAction);

const deleteTeamPrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teamDeletedEvent = createAction("teamList/deleteTeam", deleteTeamPrepareAction);