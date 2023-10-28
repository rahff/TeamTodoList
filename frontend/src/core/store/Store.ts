import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { todoLitsSlice } from "./todo-lists/TodoListsState";
import { teamListSlice } from "./team-list/TeamListState";
import { teamDetailsSlice } from "./team-details/TeamDetailsState";
import { teammateListSlice } from "./teammate-list/TeammateListState";
import { todoListDetailsSlice } from "./todoList-details/TodoListDetailsState";
import { GLOBAL_INITIAL_STATE } from "./shared/inMemory.store";
import { accountDetailsSlice } from "./account-details/AccountDetailsState";



const rootReducer = combineReducers({
    [teamDetailsSlice.name]: teamDetailsSlice.reducer,
    [todoListDetailsSlice.name]: todoListDetailsSlice.reducer,
    [teammateListSlice.name]: teammateListSlice.reducer,
    [todoLitsSlice.name]: todoLitsSlice.reducer,
    [teamListSlice.name]: teamListSlice.reducer,
    [accountDetailsSlice.name]: accountDetailsSlice.reducer,
});

export type Store = ToolkitStore<GlobalState>;

export const createStore = (initialState: GlobalState): Store => configureStore({
    reducer: rootReducer,
    preloadedState: initialState
})

export type GlobalState = ReturnType<typeof rootReducer>;
export const store = createStore(GLOBAL_INITIAL_STATE);