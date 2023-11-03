
import { TodoListDetailsViewModel } from "src/core/store/todoList-details/TodoListDetailsState";
import { Todo } from "../../../../model/todo/Todo";
import { TodoList } from "../../../../model/todo/TodoList";





export const todoListsForTeamRef = [

    {
        createdAt: new Date(2022, 23, 8).toISOString(),
        id: "qsdfoliuytredfghjk",
        name: "Facebook ads campaign for Nivea cream"
    },
    {
        createdAt: new Date(2022, 22, 8).toISOString(),
        id: "pejfjefzsfdefjekke",
        name: "Graphical assets for Nivea cream ads"
    },
    {
        createdAt: new Date(2022, 24, 8).toISOString(),
        id: "ejfjekeejfhejekedjhl",
        name: "Google ads campaign for Nivea cream"
    },
    {
        createdAt: new Date(2022, 25, 8).toISOString(),
        id: "bbedghskjhdkdjhsklssj",
        name: "Optimizing SEO for Nivea website"
    }
]

export const todoListsForTeamRefAfterDeletingTheSecond = [

    {
        createdAt: new Date(2022, 23, 8).toISOString(),
        id: "qsdfoliuytredfghjk",
        name: "Facebook ads campaign for Nivea cream"
    },
    {
        createdAt: new Date(2022, 24, 8).toISOString(),
        id: "ejfjekeejfhejekedjhl",
        name: "Google ads campaign for Nivea cream"
    },
    {
        createdAt: new Date(2022, 25, 8).toISOString(),
        id: "bbedghskjhdkdjhsklssj",
        name: "Optimizing SEO for Nivea website"
    }
]

export const todoListsForTeamRefAfterAddingOne = [

    {
        createdAt: new Date(2022, 23, 8).toISOString(),
        id: "qsdfoliuytredfghjk",
        name: "Facebook ads campaign for Nivea cream"
    },
    {
        createdAt: new Date(2022, 22, 8).toISOString(),
        id: "pejfjefzsfdefjekke",
        name: "Graphical assets for Nivea cream ads"
    },
    {
        createdAt: new Date(2022, 24, 8).toISOString(),
        id: "ejfjekeejfhejekedjhl",
        name: "Google ads campaign for Nivea cream"
    },
    {
        createdAt: new Date(2022, 25, 8).toISOString(),
        id: "bbedghskjhdkdjhsklssj",
        name: "Optimizing SEO for Nivea website"
    },
    {
        createdAt: new Date(2022, 28, 8).toISOString(),
        id: "kopmsbcvsyqlkzkjhjf",
        name: "Prepare meeting with investissors"
    }
]

export const newTeamTodoList: TodoList = {
    createdAt: new Date(2022, 28, 8).toISOString(),
    id: "kopmsbcvsyqlkzkjhjf",
    name: "Prepare meeting with investissors"
}


export const todoListsForTeamRefAfterDeleteFirst = [
    {
        createdAt: new Date(2022, 22, 8).toISOString(),
        id: "pejfjefzsfdefjekke",
        name: "Graphical assets for Nivea cream ads"
    },
    {
        createdAt: new Date(2022, 24, 8).toISOString(),
        id: "ejfjekeejfhejekedjhl",
        name: "Google ads campaign for Nivea cream"
    },
    {
        createdAt: new Date(2022, 25, 8).toISOString(),
        id: "bbedghskjhdkdjhsklssj",
        name: "Optimizing SEO for Nivea website"
    }
]

export const todoListsForTeammateRef: TodoList[] = [

    {
        createdAt: new Date(2022, 23, 8).toISOString(),
        id: "rfjrjfkdkjdjdkmlzkj",
        name: "Analyse data from Nivea data source"
    },
    {
        createdAt: new Date(2022, 22, 8).toISOString(),
        id: "djknbfjfjrbnjjrjrkrj",
        name: "Make global strategy for ads campaign"
    },
    {
        createdAt: new Date(2022, 24, 8).toISOString(),
        id: "kdkdjhddjhfjhjksl",
        name: "Build chart from previous campaign data"
    }
]

export const todoListsForTeammateRefAfterDeletingOne: TodoList[] = [

    {
        createdAt: new Date(2022, 22, 8).toISOString(),
        id: "djknbfjfjrbnjjrjrkrj",
        name: "Make global strategy for ads campaign"
    },
    {
        createdAt: new Date(2022, 24, 8).toISOString(),
        id: "kdkdjhddjhfjhjksl",
        name: "Build chart from previous campaign data"
    }
]

export const todoListsForTeammateRefAfterAddingOne: TodoList[] = [

    {
        createdAt: new Date(2022, 23, 8).toISOString(),
        id: "rfjrjfkdkjdjdkmlzkj",
        name: "Analyse data from Nivea data source"
    },
    {
        createdAt: new Date(2022, 22, 8).toISOString(),
        id: "djknbfjfjrbnjjrjrkrj",
        name: "Make global strategy for ads campaign"
    },
    {
        createdAt: new Date(2022, 24, 8).toISOString(),
        id: "kdkdjhddjhfjhjksl",
        name: "Build chart from previous campaign data"
    },
    {
        createdAt: new Date(2022, 27, 8).toISOString(),
        id: "nbvvcusrtizpdkdfhfg",
        name: "Build ppt from meetup"
    }
]

export const newPersonnalTodo: TodoList = {
    createdAt: new Date(2022, 27, 8).toISOString(),
    id: "nbvvcusrtizpdkdfhfg",
    name: "Build ppt from meetup"
}


export const todosFromTeammateTodoList: Todo[] = [
    {
        id: 'pahdoorunndijze',
        description: "complete task 1",
        deadline: new Date(2022, 10, 25).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: true
    },
    {
        id: 'hjszozjuejedkedjed',
        description: "complete task 2",
        deadline: new Date(2022, 10, 26).toISOString(),
        createdAt: new Date(2022, 10, 25).toISOString(),
        done: false
    },
    {
        id: 'cvyettrzihejhrde',
        description: "complete task 3",
        deadline: new Date(2022, 10, 27).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: false
    },
    {
        id: 'bvgfzuedhdedhrkdhed',
        description: "complete task 4",
        deadline: new Date(2022, 10, 28).toISOString(),
        createdAt: new Date(2022, 10, 24).toISOString(),
        done: false
    }

]

export const todosFromTeammateTodoListAfterDeleteOne: Todo[] = [
    {
        id: 'pahdoorunndijze',
        description: "complete task 1",
        deadline: new Date(2022, 10, 25).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: true
    },
    {
        id: 'cvyettrzihejhrde',
        description: "complete task 3",
        deadline: new Date(2022, 10, 27).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: false
    },
    {
        id: 'bvgfzuedhdedhrkdhed',
        description: "complete task 4",
        deadline: new Date(2022, 10, 28).toISOString(),
        createdAt: new Date(2022, 10, 24).toISOString(),
        done: false
    }

]


export const todosFromTeammateTodoListAfterDoneTask2: Todo[] = [
    {
        id: 'pahdoorunndijze',
        description: "complete task 1",
        deadline: new Date(2022, 10, 25).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: true
    },
    {
        id: 'hjszozjuejedkedjed',
        description: "complete task 2",
        deadline: new Date(2022, 10, 26).toISOString(),
        createdAt: new Date(2022, 10, 25).toISOString(),
        done: true
    },
    {
        id: 'cvyettrzihejhrde',
        description: "complete task 3",
        deadline: new Date(2022, 10, 27).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: false
    },
    {
        id: 'bvgfzuedhdedhrkdhed',
        description: "complete task 4",
        deadline: new Date(2022, 10, 28).toISOString(),
        createdAt: new Date(2022, 10, 24).toISOString(),
        done: false
    }

]



export const todosFromTeammateTodoListAfterAddingOne: Todo[] = [
    {
        id: 'pahdoorunndijze',
        description: "complete task 1",
        deadline: new Date(2022, 10, 25).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: true
    },
    {
        id: 'hjszozjuejedkedjed',
        description: "complete task 2",
        deadline: new Date(2022, 10, 26).toISOString(),
        createdAt: new Date(2022, 10, 25).toISOString(),
        done: false
    },
    {
        id: 'cvyettrzihejhrde',
        description: "complete task 3",
        deadline: new Date(2022, 10, 27).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: false
    },
    {
        id: 'bvgfzuedhdedhrkdhed',
        description: "complete task 4",
        deadline: new Date(2022, 10, 28).toISOString(),
        createdAt: new Date(2022, 10, 24).toISOString(),
        done: false
    },
    {
        id: 'yqsuejndodjhfd',
        description: "complete task 5",
        deadline: new Date(2022, 10, 25).toISOString(),
        createdAt: new Date(2022, 10, 23).toISOString(),
        done: false
    }

]

export const teammateTodoListDetails: TodoListDetailsViewModel = {
    viewModel: {
        info: {
            id: "rfjrjfkdkjdjdkmlzkj",
            createdAt: new Date(2022, 23, 8).toISOString(),
            name: "Analyse data from Nivea data source",

        },
        todos: todosFromTeammateTodoList
    }
}

export const teammateTodoListDetailsAfterAddingOneTodo: TodoListDetailsViewModel = {
    viewModel: {
        info: {
            id: "rfjrjfkdkjdjdkmlzkj",
        
            createdAt: new Date(2022, 23, 8).toISOString(),
            name: "Analyse data from Nivea data source",
        },
        todos: todosFromTeammateTodoListAfterAddingOne
    }
}

export const newTodo: Todo = {
    id: 'yqsuejndodjhfd',
    description: "complete task 5",
    deadline: new Date(2022, 10, 25).toISOString(),
    createdAt: new Date(2022, 10, 23).toISOString(),
    done: false
}




