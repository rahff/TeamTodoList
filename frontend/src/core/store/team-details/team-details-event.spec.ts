import { newTeammate } from "src/core/application/team/in-memory/data/team.data"
import { createStore } from "../Store"
import { GLOBAL_FAKE_STATE, GLOBAL_INITIAL_STATE } from "../shared/inMemory.store"
import { teamDetailsReceivedEvent, teamTodoListAddedEvent, teamTodoListDeletedEvent, teammateRemovedFromTeamEvent, teammatesOnTeamAddedEvent } from "./Events"
import { TeamDetailsStoreApi } from "./TeamDetailsStoreApi"
import { addedTodoList, teamDetailsFakeState, teamDetailsFakeStateAfterAddingOneTodoList, teamDetailsFakeStateAfterAddingTeammates, teamDetailsFakeStateAfterDelete2ndTodoList, teamDetailsFakeStateAfterRemoveMikki } from "./data/inMemory.store"



describe("TeamDetailsViewSlice", () => {


    it("when teamDetailsReceivedEvent fired", () => {
        const storeApi = new TeamDetailsStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
        const event = teamDetailsReceivedEvent(teamDetailsFakeState);
        storeApi.fireEvent(event);
        storeApi.getTeamDetails().subscribe((teamDetails) => {
            expect(teamDetails).toEqual(teamDetailsFakeState.viewModel.details)
        })
    })
    
    it("when teamTodoListDeletedEvent fired", () => {
        const storeApi = new TeamDetailsStoreApi(createStore({...GLOBAL_FAKE_STATE}));
        const event = teamTodoListDeletedEvent("todoList2_OfTeam2_id");
        storeApi.fireEvent(event);
        storeApi.getTeamDetails().subscribe((teamDetails) => {
            expect(teamDetails).toEqual(teamDetailsFakeStateAfterDelete2ndTodoList.viewModel.details)
        })
    });

    it("when teamTodoListAddedEvent fired", () => {
        const storeApi = new TeamDetailsStoreApi(createStore({...GLOBAL_FAKE_STATE}));
        const event = teamTodoListAddedEvent(addedTodoList);
        storeApi.fireEvent(event);
        storeApi.getTeamDetails().subscribe((teamDetails) => {
            expect(teamDetails).toEqual(teamDetailsFakeStateAfterAddingOneTodoList.viewModel.details)
        })
    });

    it("when teammateRemovedFromTeamEvent fired", () => {
        const storeApi = new TeamDetailsStoreApi(createStore({...GLOBAL_FAKE_STATE}));
        const event = teammateRemovedFromTeamEvent("teammate_Mikki_id");
        storeApi.fireEvent(event);
        storeApi.getTeamDetails().subscribe((teamDetails) => {
            expect(teamDetails).toEqual(teamDetailsFakeStateAfterRemoveMikki.viewModel.details)
        })
    })

    it("when teammatesOnTeamAddedEvent fired", () => {
        const storeApi = new TeamDetailsStoreApi(createStore({...GLOBAL_FAKE_STATE}));
        const event = teammatesOnTeamAddedEvent([newTeammate]);
        storeApi.fireEvent(event);
        storeApi.getTeamDetails().subscribe((teamDetails) => {
            expect(teamDetails).toEqual(teamDetailsFakeStateAfterAddingTeammates.viewModel.details)
        })
    })
})