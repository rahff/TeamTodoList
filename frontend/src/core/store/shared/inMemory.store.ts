import { GlobalState } from "../Store";
import { teamDetailsFakeState } from "../team-details/data/inMemory.store";
import { teamListView } from "../team-list/data/inMemory.store";
import { teammateList } from "../teammate-list/data/inMemory.store";
import { todoListsFakeState } from "../todo-lists/data/inMemory.store";
import { todoListDetailsFakeState } from "../todoList-details/data/inMemory.store";

export const GLOBAL_INITIAL_STATE: GlobalState = {
    teamDetails: {
        details: null
    },
    todoLists: {
        lists: []
    },
    teamList: {
        list: []
    },
    teammateList: {
        list: []
    },
    todoListDetails: {
        details: {
            info: null,
            todos: []
        }
    }
}


export const GLOBAL_FAKE_STATE: GlobalState = {
    teamList: teamListView,
    teamDetails: teamDetailsFakeState,
    teammateList: teammateList,
    todoLists: todoListsFakeState,
    todoListDetails: todoListDetailsFakeState
}