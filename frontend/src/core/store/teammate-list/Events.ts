import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { TeammateListViewModel } from "./TeammateListState";
import { Teammate } from "src/core/model/team/Teammate";


const setTeammateListPrepareAction: PrepareAction<TeammateListViewModel> = (payload: TeammateListViewModel) =>({payload});
export const teammateListReceivedEvent = createAction("teammateList/setTeammateList", setTeammateListPrepareAction);

const addTeammatePrepareAction: PrepareAction<Teammate> = (payload:  Teammate) =>({payload});
export const teammateJoinedEvent = createAction("teammateList/addTeammate", addTeammatePrepareAction);

const fireTeammatePrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teammateFiredEvent = createAction("teammateList/fireTeammate", fireTeammatePrepareAction);
