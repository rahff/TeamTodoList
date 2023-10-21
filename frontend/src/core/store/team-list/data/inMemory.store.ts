import { Team } from "src/core/application/team/dto/Team";
import { TeamListView } from "../TeamListState";



export const teamListView: TeamListView = {
    list:  [
        {
            id: "team1_id",
            name: "Team1",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff"
                },
                {
                    email: "mikki@gmail.com",
                    id: "teammate_Mikki_id",
                    name: "Mikki"
                },
            ],
            todoLists: []
        },
        {
            id: "team2_id",
            name: "Team2",
            teammates: [
                {
                    email: "roger@gmail.com",
                    id: "teammate_Roger_id",
                    name: "Roger"
                },
                {
                    email: "sarah@gmail.com",
                    id: "teammate_Sarah_id",
                    name: "Sarah"
                },
            ],
            todoLists: [
        
                {
                    createdAt: new Date(2022, 23, 8).toISOString(),
                    id: "todoList1_of_team2_id",
                    name: "Facebook ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 22, 8).toISOString(),
                    id: "todoList2_of_team2_id",
                    name: "Graphical assets for Nivea cream ads"
                },
                {
                    createdAt: new Date(2022, 24, 8).toISOString(),
                    id: "todoList3_of_team2_id",
                    name: "Google ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 25, 8).toISOString(),
                    id: "todoList4_of_team2_id",
                    name: "Optimizing SEO for Nivea website"
                }
            ]
        },
        {
            id: "team3_id",
            name: "Team3",
            teammates: [
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
            ],
            todoLists: []
        },
    ]
}

export const team4: Team = {
    id: "team4_id",
    name: "Team4",
    teammates: [
        {
            email: "gaston@gmail.com",
            id: "teammate_Gaston_id",
            name: "Gaston"
        },
        {
            email: "myriam@gmail.com",
            id: "teammate_Myriam_id",
            name: "Myriam"
        },
    ],
    todoLists: []
}

export const teamListViewAfterTeam4Added: TeamListView = {
    list:  [
        {
            id: "team1_id",
            name: "Team1",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff"
                },
                {
                    email: "mikki@gmail.com",
                    id: "teammate_Mikki_id",
                    name: "Mikki"
                },
            ],
            todoLists: []
        },
        {
            id: "team2_id",
            name: "Team2",
            teammates: [
                {
                    email: "roger@gmail.com",
                    id: "teammate_Roger_id",
                    name: "Roger"
                },
                {
                    email: "sarah@gmail.com",
                    id: "teammate_Sarah_id",
                    name: "Sarah"
                },
            ],
            todoLists: [
        
                {
                    createdAt: new Date(2022, 23, 8).toISOString(),
                    id: "todoList1_of_team2_id",
                    name: "Facebook ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 22, 8).toISOString(),
                    id: "todoList2_of_team2_id",
                    name: "Graphical assets for Nivea cream ads"
                },
                {
                    createdAt: new Date(2022, 24, 8).toISOString(),
                    id: "todoList3_of_team2_id",
                    name: "Google ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 25, 8).toISOString(),
                    id: "todoList4_of_team2_id",
                    name: "Optimizing SEO for Nivea website"
                }
            ]
        },
        {
            id: "team3_id",
            name: "Team3",
            teammates: [
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
            ],
            todoLists: []
        },
        team4
    ]
}



export const teamListViewAfterDeletingTeam2: TeamListView = {
    list:  [
        {
            id: "team1_id",
            name: "Team1",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff"
                },
                {
                    email: "mikki@gmail.com",
                    id: "teammate_Mikki_id",
                    name: "Mikki"
                },
            ],
            todoLists: []
        },
        {
            id: "team3_id",
            name: "Team3",
            teammates: [
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
            ],
            todoLists: []
        },
    ]
}