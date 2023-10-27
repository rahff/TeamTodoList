import { Teammate } from "src/core/application/team/dto/Teammate";
import { TeammateListViewModel } from "../TeammateListState";

export const recrue: Teammate = {
    email: "bob@gmail.com",
    id: "teammate_Bob_id",
    name: "Bob",
    teamId: "team2_id"
}

export const teammateList: TeammateListViewModel = {
    viewModel: {

        list: [
            {
                email: "greg@gmail.com",
                id: "teammate_Greg_id",
                name: "Greg",
                teamId: "team1_id"
            },
            {
                email: "jacqueline@gmail.com",
                id: "teammate_Jacquelline_id",
                name: "Jacqueline",
                teamId: "team1_id"
            },
            {
                email: "julie@gmail.com",
                id: "teammate_Julie_id",
                name: "Julie",
                teamId: "team2_id"
            },
            {
                email: "michel@gmail.com",
                id: "teammate_Michel_id",
                name: "Michel",
                teamId: "team2_id"
            },
            {
                email: "rahff@gmail.com",
                id: "teammate_Rahff_id",
                name: "Rahff",
                teamId: "team3_id"
            },
            {
                email: "mikki@gmail.com",
                id: "teammate_Mikki_id",
                name: "Mikki",
                teamId: "team3_id"
            }
        ]
    }
}


export const teammateListAfterMichelFired: TeammateListViewModel = {
    viewModel: {

        list: [
            {
                email: "greg@gmail.com",
                id: "teammate_Greg_id",
                name: "Greg",
                teamId: "team1_id"
            },
            {
                email: "jacqueline@gmail.com",
                id: "teammate_Jacquelline_id",
                name: "Jacqueline",
                teamId: "team1_id"
            },
            {
                email: "julie@gmail.com",
                id: "teammate_Julie_id",
                name: "Julie",
                teamId: "team2_id"
            },
            {
                email: "rahff@gmail.com",
                id: "teammate_Rahff_id",
                name: "Rahff",
                teamId: "team3_id"
            },
            {
                email: "mikki@gmail.com",
                id: "teammate_Mikki_id",
                name: "Mikki",
                teamId: "team3_id"
            }
        ]
    }
}