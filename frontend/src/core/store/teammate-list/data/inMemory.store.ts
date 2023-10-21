import { Teammate } from "src/core/application/team/dto/Teammate";
import { TeammateListView } from "../TeammateListState";

export const teammateListInitialState: TeammateListView = {
    list: []
}

export const recrue: Teammate = {
    email: "bob@gmail.com",
    id: "teammate_Bob_id",
    name: "Bob"
}

export const teammateList: TeammateListView = {
    list: [
        {
            email: "greg@gmail.com",
            id: "teammate_Greg_id",
            name: "Greg"
        },
        {
            email: "jacqueline@gmail.com",
            id: "teammate_Jacquelline_id",
            name: "Jacqueline"
        },
        {
            email: "julie@gmail.com",
            id: "teammate_Julie_id",
            name: "Julie"
        },
        {
            email: "michel@gmail.com",
            id: "teammate_Michel_id",
            name: "Michel"
        },
        {
            email: "rahff@gmail.com",
            id: "teammate_Rahff_id",
            name: "Rahff"
        },
        {
            email: "mikki@gmail.com",
            id: "teammate_Mikki_id",
            name: "Mikki"
        }
    ]
}


export const teammateListAfterMichelFired: TeammateListView = {
    list: [
        {
            email: "greg@gmail.com",
            id: "teammate_Greg_id",
            name: "Greg"
        },
        {
            email: "jacqueline@gmail.com",
            id: "teammate_Jacquelline_id",
            name: "Jacqueline"
        },
        {
            email: "julie@gmail.com",
            id: "teammate_Julie_id",
            name: "Julie"
        },
        {
            email: "rahff@gmail.com",
            id: "teammate_Rahff_id",
            name: "Rahff"
        },
        {
            email: "mikki@gmail.com",
            id: "teammate_Mikki_id",
            name: "Mikki"
        }
    ]
}