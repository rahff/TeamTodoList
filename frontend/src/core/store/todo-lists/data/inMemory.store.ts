import { TodoList } from "src/core/application/todo/dto/TodoList";
import { TodoListsViewModel } from "../TodoListsState";



export const todoListsFakeState: TodoListsViewModel = {
    viewModel: {
        lists: [

            {
                createdAt: new Date(2022, 23, 8).toISOString(),
                id: "todoList1_of_user",
                name: "Facebook ads campaign for Nivea cream"
            },
            {
                createdAt: new Date(2022, 22, 8).toISOString(),
                id: "todoList2_of_user",
                name: "Graphical assets for Nivea cream ads"
            },
            {
                createdAt: new Date(2022, 24, 8).toISOString(),
                id: "todoList3_of_user",
                name: "Google ads campaign for Nivea cream"
            },
            {
                createdAt: new Date(2022, 25, 8).toISOString(),
                id: "todoList4_of_user",
                name: "Optimizing SEO for Nivea website"
            }
        ]
    }
}


export const todoListsFakeStateAfterDeleteSecond: TodoListsViewModel = {
    viewModel: {
        lists: [

            {
                createdAt: new Date(2022, 23, 8).toISOString(),
                id: "todoList1_of_user",
                name: "Facebook ads campaign for Nivea cream"
            },
            {
                createdAt: new Date(2022, 24, 8).toISOString(),
                id: "todoList3_of_user",
                name: "Google ads campaign for Nivea cream"
            },
            {
                createdAt: new Date(2022, 25, 8).toISOString(),
                id: "todoList4_of_user",
                name: "Optimizing SEO for Nivea website"
            }
        ]
    }
}

export const newTodoList: TodoList = {
    createdAt: new Date(2022, 28, 8).toISOString(),
    id: "todoList5_of_user",
    name: "Prepare meeting with investissors"
}
