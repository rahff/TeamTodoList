import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { TeammateListView } from "./TeammateListState";
import { Teammate } from "src/core/application/team/dto/Teammate";

const setTeammateListPrepareAction: PrepareAction<TeammateListView> = (payload: TeammateListView) =>({payload});
export const teammateListReceivedEvent = createAction("teammateList/setTeammateList", setTeammateListPrepareAction);

const addTeammatePrepareAction: PrepareAction<Teammate> = (payload:  Teammate) =>({payload});
export const teammateAddedEvent = createAction("teammateList/addTeammate", addTeammatePrepareAction);

const fireTeammatePrepareAction: PrepareAction<string> = (payload:  string) =>({payload});
export const teammateFiredEvent = createAction("teammateList/fireTeammate", fireTeammatePrepareAction);
