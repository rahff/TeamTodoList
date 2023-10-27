import { Teammate } from "src/core/application/team/dto/Teammate";
import { createStore } from "../Store";
import { teammateJoinedEvent, teammateFiredEvent, teammateListReceivedEvent } from "./Events";
import { TeammateListStoreApi } from "./TeammateListStoreApi";
import { GLOBAL_FAKE_STATE, GLOBAL_INITIAL_STATE } from "../shared/inMemory.store";
import { recrue, teammateList, teammateListAfterMichelFired } from "./data/inMemory.store";

describe("TeammateListViewSlice", () => {

    it("when teamListReceived event fired", () => {
        const store = createStore({...GLOBAL_INITIAL_STATE});
        const storeApi = new TeammateListStoreApi(store);
        const event = teammateListReceivedEvent(teammateList);
        storeApi.fireEvent(event);
        storeApi.getTeammateList().subscribe((list: Teammate[]) => {
            expect(list).toEqual(teammateList.viewModel.list)
        })
    });

   
    it("when teamAdded event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TeammateListStoreApi(store);
        const event = teammateJoinedEvent(recrue);
        storeApi.fireEvent(event);
        storeApi.getTeammateList().subscribe((list: Teammate[]) => {
            expect(list).toEqual([...teammateList.viewModel.list, recrue]);
        });
    });
    
    it("when teamDeleted event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TeammateListStoreApi(store);
        const event = teammateFiredEvent("teammate_Michel_id");
        storeApi.fireEvent(event);
        storeApi.getTeammateList().subscribe((list: Teammate[]) => {
            expect(list).toEqual(teammateListAfterMichelFired.viewModel.list);
        });
    })
})