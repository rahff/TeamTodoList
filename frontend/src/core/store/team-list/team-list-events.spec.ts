import { Team } from "src/core/application/team/dto/Team";
import { createStore } from "../Store";
import { teamListReceivedEvent, teamCreatedEvent, teamDeletedEvent } from "./Events";
import { TeamListStoreApi } from "./TeamListStoreApi";
import { GLOBAL_FAKE_STATE, GLOBAL_INITIAL_STATE } from "../shared/inMemory.store";
import { team4, teamListView, teamListViewAfterDeletingTeam2, teamListViewAfterTeam4Added } from "./data/inMemory.store";



describe("TeamListSlice", () => {

    it("when teamListReceived event fired", () => {
        const store = createStore({...GLOBAL_INITIAL_STATE});
        const storeApi = new TeamListStoreApi(store);
        const event = teamListReceivedEvent(teamListView);
        storeApi.fireEvent(event);
        storeApi.getTeamList().subscribe((list: Team[]) => {
            expect(list).toEqual(teamListView.viewModel.list)
        })
    });

    it("when teamAdded event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TeamListStoreApi(store);
        const event = teamCreatedEvent(team4);
        storeApi.fireEvent(event);
        storeApi.getTeamList().subscribe((list: Team[]) => {
            expect(list).toEqual(teamListViewAfterTeam4Added.viewModel.list);
        });
    });

    it("when teamDeleted event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TeamListStoreApi(store);
        const event = teamDeletedEvent("team2_id");
        storeApi.fireEvent(event);
        storeApi.getTeamList().subscribe((list: Team[]) => {
            expect(list).toEqual(teamListViewAfterDeletingTeam2.viewModel.list);
        });
    });
})