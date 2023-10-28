import { GlobalState } from "../Store";
import { accountDetailsFakeState } from "../account-details/data/inMemory.store";
import { teamDetailsFakeState } from "../team-details/data/inMemory.store";
import { teamListView } from "../team-list/data/inMemory.store";
import { teammateList } from "../teammate-list/data/inMemory.store";
import { todoListsFakeState } from "../todo-lists/data/inMemory.store";
import { todoListDetailsFakeState } from "../todoList-details/data/inMemory.store";


export const GLOBAL_INITIAL_STATE: GlobalState = {
    teamDetails: {
        viewModel: {
            details: null,
            availableTeammates: []
        }
    },
    todoLists: {
        viewModel: {
            lists: []
        }
    },
    teamList: {
        viewModel: {
            list: []
        }
    },
    teammateList: {
        viewModel: {
            list: []
        }
    },
    todoListDetails: {
        viewModel: {
            info: null,
            todos: []
        }
    },
    accountDetails: {
        viewModel: {
            user: null
        }
    }
}


export const GLOBAL_FAKE_STATE: GlobalState = {
    teamList: teamListView,
    teamDetails: teamDetailsFakeState,
    teammateList: teammateList,
    todoLists: todoListsFakeState,
    todoListDetails: todoListDetailsFakeState,
    accountDetails: accountDetailsFakeState
}