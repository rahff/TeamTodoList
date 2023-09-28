import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { todoLitsSlice } from "./todo/TodoListsState";
import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";



const rootReducer = combineReducers({
    [todoLitsSlice.name]: todoLitsSlice.reducer
})

export const createStore = (preLoadState?: GlobalState): ToolkitStore<GlobalState> => configureStore({
    reducer: rootReducer,
    preloadedState: preLoadState
})

export type GlobalState = ReturnType<typeof rootReducer>;