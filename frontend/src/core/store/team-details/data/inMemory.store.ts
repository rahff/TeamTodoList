
import { TodoList } from "src/core/model/todo/TodoList";
import { TeamDetailsViewModel } from "../TeamDetailsState";
import { newTeammate } from "src/core/application/team/in-memory/data/team.data";


export const teamDetailsFakeState: TeamDetailsViewModel = {
    viewModel: {

        details: {
            id: "team2_Id",
            name: "team2",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff",
                    teamId: "team2_Id"
                },
                {
                    email: "mikki@gmail.com",
                    id: "teammate_Mikki_id",
                    name: "Mikki",
                    teamId: "team2_Id"
                },
            ],
            todoLists: [
    
                {
                    createdAt: new Date(2022, 23, 8).toISOString(),
                    id: "todoList1_OfTeam2_id",
                    name: "Facebook ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 22, 8).toISOString(),
                    id: "todoList2_OfTeam2_id",
                    name: "Graphical assets for Nivea cream ads"
                },
                {
                    createdAt: new Date(2022, 24, 8).toISOString(),
                    id: "todoList3_OfTeam2_id",
                    name: "Google ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 25, 8).toISOString(),
                    id: "todoList4_OfTeam2_id",
                    name: "Optimizing SEO for Nivea website"
                }
            ]
        },
        availableTeammates: []
    }
}

export const teamDetailsFakeStateAfterAddingTeammates: TeamDetailsViewModel = {
    viewModel: {

        details: {
            id: "team2_Id",
            name: "team2",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff",
                    teamId: "team2_Id"
                },
                {
                    email: "mikki@gmail.com",
                    id: "teammate_Mikki_id",
                    name: "Mikki",
                    teamId: "team2_Id"
                },
                newTeammate
            ],
            todoLists: [
    
                {
                    createdAt: new Date(2022, 23, 8).toISOString(),
                    id: "todoList1_OfTeam2_id",
                    name: "Facebook ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 22, 8).toISOString(),
                    id: "todoList2_OfTeam2_id",
                    name: "Graphical assets for Nivea cream ads"
                },
                {
                    createdAt: new Date(2022, 24, 8).toISOString(),
                    id: "todoList3_OfTeam2_id",
                    name: "Google ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 25, 8).toISOString(),
                    id: "todoList4_OfTeam2_id",
                    name: "Optimizing SEO for Nivea website"
                }
            ]
        },
        availableTeammates: []
    }
}

export const teamDetailsFakeStateAfterRemoveMikki: TeamDetailsViewModel = {
    viewModel: {

        details: {
            id: "team2_Id",
            name: "team2",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff",
                    teamId: "team2_Id"
                }
            ],
            todoLists: [
    
                {
                    createdAt: new Date(2022, 23, 8).toISOString(),
                    id: "todoList1_OfTeam2_id",
                    name: "Facebook ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 22, 8).toISOString(),
                    id: "todoList2_OfTeam2_id",
                    name: "Graphical assets for Nivea cream ads"
                },
                {
                    createdAt: new Date(2022, 24, 8).toISOString(),
                    id: "todoList3_OfTeam2_id",
                    name: "Google ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 25, 8).toISOString(),
                    id: "todoList4_OfTeam2_id",
                    name: "Optimizing SEO for Nivea website"
                }
            ]
        },
        availableTeammates: []
    }
}


export const teamDetailsFakeStateAfterDelete2ndTodoList: TeamDetailsViewModel = {
    viewModel: {

        details: {
            id: "team2_Id",
            name: "team2",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff",
                    teamId: "team2_Id"
                },
                {
                    email: "mikki@gmail.com",
                    id: "teammate_Mikki_id",
                    name: "Mikki",
                    teamId: "team2_Id"
                },
            ],
            todoLists: [
    
                {
                    createdAt: new Date(2022, 23, 8).toISOString(),
                    id: "todoList1_OfTeam2_id",
                    name: "Facebook ads campaign for Nivea cream"
                },
               
                {
                    createdAt: new Date(2022, 24, 8).toISOString(),
                    id: "todoList3_OfTeam2_id",
                    name: "Google ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 25, 8).toISOString(),
                    id: "todoList4_OfTeam2_id",
                    name: "Optimizing SEO for Nivea website"
                }
            ]
        },
        availableTeammates: []
    }
}

export const addedTodoList: TodoList = {
    createdAt: new Date(2022, 26, 8).toISOString(),
    id: "todoList5_OfTeam2_id",
    name: "X event \'s Organization"
}

export const teamDetailsFakeStateAfterAddingOneTodoList: TeamDetailsViewModel = {
    viewModel: {

        details: {
            id: "team2_Id",
            name: "team2",
            teammates: [
                {
                    email: "rahff@gmail.com",
                    id: "teammate_Rahff_id",
                    name: "Rahff",
                    teamId: "team2_Id"
                },
                {
                    email: "mikki@gmail.com",
                    id: "teammate_Mikki_id",
                    name: "Mikki",
                    teamId: "team2_Id"
                },
            ],
            todoLists: [
    
                {
                    createdAt: new Date(2022, 23, 8).toISOString(),
                    id: "todoList1_OfTeam2_id",
                    name: "Facebook ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 22, 8).toISOString(),
                    id: "todoList2_OfTeam2_id",
                    name: "Graphical assets for Nivea cream ads"
                },
                {
                    createdAt: new Date(2022, 24, 8).toISOString(),
                    id: "todoList3_OfTeam2_id",
                    name: "Google ads campaign for Nivea cream"
                },
                {
                    createdAt: new Date(2022, 25, 8).toISOString(),
                    id: "todoList4_OfTeam2_id",
                    name: "Optimizing SEO for Nivea website"
                },
                addedTodoList
                
            ]
        },
        availableTeammates: []
    }
}