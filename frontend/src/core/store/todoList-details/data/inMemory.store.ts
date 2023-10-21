import { TodoListDetailsView } from "../TodoListDetailsState";

export const todoListDetailsInitialState: TodoListDetailsView = {
    details: {
        info: null,
        todos: []
    }
}

export const task5 = {
    id: 'todo5_of_todoList',
    description: "complete task 5",
    deadline: new Date(2022, 10, 26).toISOString(),
    createdAt: new Date(2022, 10, 25).toISOString(),
    done: false
}

export const todoListDetailsFakeState: TodoListDetailsView = {
    details: {
        info: {
            createdAt: new Date(2022, 27, 8).toISOString(),
            id: "todoList_details_id",
            name: "Meetup\'s organization for special event"
        },
        todos: [
            {
                id: 'todo1_of_todoList',
                description: "complete task 1",
                deadline: new Date(2022, 10, 25).toISOString(),
                createdAt: new Date(2022, 10, 23).toISOString(),
                done: true
            },
            {
                id: 'todo2_of_todoList',
                description: "complete task 2",
                deadline: new Date(2022, 10, 26).toISOString(),
                createdAt: new Date(2022, 10, 25).toISOString(),
                done: false
            },
            {
                id: 'todo3_of_todoList',
                description: "complete task 3",
                deadline: new Date(2022, 10, 27).toISOString(),
                createdAt: new Date(2022, 10, 23).toISOString(),
                done: false
            },
            {
                id: 'todo4_of_todoList',
                description: "complete task 4",
                deadline: new Date(2022, 10, 28).toISOString(),
                createdAt: new Date(2022, 10, 24).toISOString(),
                done: false
            }
        
        ]
    }
}

export const todoListDetailsFakeStateAfterDoneTask2: TodoListDetailsView = {
    details: {
        info: {
            createdAt: new Date(2022, 27, 8).toISOString(),
            id: "todoList_details_id",
            name: "Meetup\'s organization for special event"
        },
        todos: [
            {
                id: 'todo1_of_todoList',
                description: "complete task 1",
                deadline: new Date(2022, 10, 25).toISOString(),
                createdAt: new Date(2022, 10, 23).toISOString(),
                done: true
            },
            {
                id: 'todo2_of_todoList',
                description: "complete task 2",
                deadline: new Date(2022, 10, 26).toISOString(),
                createdAt: new Date(2022, 10, 25).toISOString(),
                done: true
            },
            {
                id: 'todo3_of_todoList',
                description: "complete task 3",
                deadline: new Date(2022, 10, 27).toISOString(),
                createdAt: new Date(2022, 10, 23).toISOString(),
                done: false
            },
            {
                id: 'todo4_of_todoList',
                description: "complete task 4",
                deadline: new Date(2022, 10, 28).toISOString(),
                createdAt: new Date(2022, 10, 24).toISOString(),
                done: false
            }
        
        ]
    }
}

export const todoListDetailsFakeStateAfterDeletingTask2: TodoListDetailsView = {
    details: {
        info: {
            createdAt: new Date(2022, 27, 8).toISOString(),
            id: "todoList_details_id",
            name: "Meetup\'s organization for special event"
        },
        todos: [
            {
                id: 'todo1_of_todoList',
                description: "complete task 1",
                deadline: new Date(2022, 10, 25).toISOString(),
                createdAt: new Date(2022, 10, 23).toISOString(),
                done: true
            },
            {
                id: 'todo3_of_todoList',
                description: "complete task 3",
                deadline: new Date(2022, 10, 27).toISOString(),
                createdAt: new Date(2022, 10, 23).toISOString(),
                done: false
            },
            {
                id: 'todo4_of_todoList',
                description: "complete task 4",
                deadline: new Date(2022, 10, 28).toISOString(),
                createdAt: new Date(2022, 10, 24).toISOString(),
                done: false
            }
        
        ]
    }
}