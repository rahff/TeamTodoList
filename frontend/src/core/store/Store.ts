import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { todoLitsSlice } from "./todo-lists/TodoListsState";
import { teamListSlice } from "./team-list/TeamListState";
import { teamDetailsSlice } from "./team-details/TeamDetailsState";
import { teammateListSlice } from "./teammate-list/TeammateListState";
import { todoListDetailsSlice } from "./todoList-details/TodoListDetailsState";



const rootReducer = combineReducers({
    [teamDetailsSlice.name]: teamDetailsSlice.reducer,
    [todoListDetailsSlice.name]: todoListDetailsSlice.reducer,
    [teammateListSlice.name]: teammateListSlice.reducer,
    [todoLitsSlice.name]: todoLitsSlice.reducer,
    [teamListSlice.name]: teamListSlice.reducer
})

export const createStore = (preLoadState?: GlobalState): ToolkitStore<GlobalState> => configureStore({
    reducer: rootReducer,
    preloadedState: preLoadState
})

export type GlobalState = ReturnType<typeof rootReducer>;