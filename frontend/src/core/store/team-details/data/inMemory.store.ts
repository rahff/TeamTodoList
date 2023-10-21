import { TodoList } from "src/core/application/todo/dto/TodoList";
import { TeamDetailsView } from "../TeamDetailsState";

export const teamDetailsInitialState: TeamDetailsView = {
    details: null
}


export const teamDetailsFakeState: TeamDetailsView = {
    details: {
        id: "team2Id",
        name: "team2",
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
    }
}

export const teamDetailsFakeStateAfterRemoveMikki: TeamDetailsView = {
    details: {
        id: "team2Id",
        name: "team2",
        teammates: [
            {
                email: "rahff@gmail.com",
                id: "teammate_Rahff_id",
                name: "Rahff"
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
    }
}


export const teamDetailsFakeStateAfterDelete2ndTodoList: TeamDetailsView = {
    details: {
        id: "team2Id",
        name: "team2",
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
    }
}

export const addedTodoList: TodoList = {
    createdAt: new Date(2022, 26, 8).toISOString(),
    id: "todoList5_OfTeam2_id",
    name: "X event \'s Organization"
}

export const teamDetailsFakeStateAfterAddingOneTodoList: TeamDetailsView = {
    details: {
        id: "team2Id",
        name: "team2",
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
    }
}