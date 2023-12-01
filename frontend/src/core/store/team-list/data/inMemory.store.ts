import { Team } from "src/core/model/team/Team";
import { TeamListViewModel } from "../TeamListState";
import { TeamCard } from "src/core/model/team/TeamCard";



export const teamListView: TeamListViewModel = {
    viewModel: {

        list:  [
            {
                id: "team1_id",
                name: "Team1",
                teammateCount: 2,
                todoListCount: 0
            },
            {
                id: "team2_id",
                name: "Team2",
                teammateCount: 2,
                todoListCount: 4
            },
            {
                id: "team3_id",
                name: "Team3",
                teammateCount: 2,
                todoListCount: 0
            },
        ],
        availableTeammates: []
    }
}

export const team4: TeamCard = {
    id: "team4_id",
    name: "Team4",
    teammateCount: 2,
    todoListCount: 0
}

export const teamListViewAfterTeam4Added: TeamListViewModel = {
    viewModel: {

        list:  [
            {
                id: "team1_id",
                name: "Team1",
                teammateCount: 2,
                todoListCount: 0
            },
            {
                id: "team2_id",
                name: "Team2",
                teammateCount: 2,
                todoListCount: 4
            },
            {
                id: "team3_id",
                name: "Team3",
                teammateCount: 2,
                todoListCount: 0
            },
            team4
        ],
        availableTeammates: []
    }
}



export const teamListViewAfterDeletingTeam2: TeamListViewModel = {
    viewModel: {

        list:  [
            {
                id: "team1_id",
                name: "Team1",
                teammateCount: 2,
                todoListCount: 0
            },
            {
                id: "team3_id",
                name: "Team3",
                teammateCount: 2,
                todoListCount: 0
            },
        ],
        availableTeammates: []
    }
}