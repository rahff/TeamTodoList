import { Team } from "../../dto/Team";
import { Teammate } from "../../dto/Teammate";



export const team2 = {
    id: "xcvbtghrtyui",
    name: "Team2",
    teammates: [
        {
            email: "roger@gmail.com",
            id: "poijbvtgfmm",
            name: "Roger"
        },
        {
            email: "sarah@gmail.com",
            id: "mplokijuhygt",
            name: "Sarah"
        },
    ],
    todoLists: [

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
}

export const teamList: Team[] = [
    {
        id: "fghjklopiuyt",
        name: "Team1",
        teammates: [
            {
                email: "rahff@gmail.com",
                id: "azertyuiopp",
                name: "Rahff"
            },
            {
                email: "mikki@gmail.com",
                id: "mlkjhgfdfghjk",
                name: "Mikki"
            },
        ],
        todoLists: []
    },
    team2,
    {
        id: "iudfdshbbcvvdg",
        name: "Team3",
        teammates: [
            {
                email: "julie@gmail.com",
                id: "mpnwsaezert",
                name: "Julie"
            },
            {
                email: "michel@gmail.com",
                id: "nhyioaqstr",
                name: "Michel"
            },
        ],
        todoLists: []
    },
]


export const teamListAfterDeleteTeam2: Team[] = [
    {
        id: "fghjklopiuyt",
        name: "Team1",
        teammates: [
            {
                email: "rahff@gmail.com",
                id: "azertyuiopp",
                name: "Rahff"
            },
            {
                email: "mikki@gmail.com",
                id: "mlkjhgfdfghjk",
                name: "Mikki"
            },
        ],
        todoLists: []
    },
    {
        id: "iudfdshbbcvvdg",
        name: "Team3",
        teammates: [
            {
                email: "julie@gmail.com",
                id: "mpnwsaezert",
                name: "Julie"
            },
            {
                email: "michel@gmail.com",
                id: "nhyioaqstr",
                name: "Michel"
            },
        ],
        todoLists: []
    },
]

export const newTeam: Team = {
    
        id: "jsupmsdhbbcuyieue",
        name: "Team4",
        teammates: [
            {
                email: "greg@gmail.com",
                id: "lkjfhdvdyhdzi",
                name: "Greg"
            },
            {
                email: "jacqueline@gmail.com",
                id: "mpetfxccxxqq",
                name: "Jacqueline"
            },
        ],
        todoLists: []
}

export const teammateList: Teammate[] = [
    {
        email: "greg@gmail.com",
        id: "lkjfhdvdyhdzi",
        name: "Greg"
    },
    {
        email: "jacqueline@gmail.com",
        id: "mpetfxccxxqq",
        name: "Jacqueline"
    },
    {
        email: "julie@gmail.com",
        id: "mpnwsaezert",
        name: "Julie"
    },
    {
        email: "michel@gmail.com",
        id: "nhyioaqstr",
        name: "Michel"
    },
    {
        email: "rahff@gmail.com",
        id: "azertyuiopp",
        name: "Rahff"
    },
    {
        email: "mikki@gmail.com",
        id: "mlkjhgfdfghjk",
        name: "Mikki"
    }
]


export const teammateListAfterFiredOne: Teammate[] = [
    {
        email: "greg@gmail.com",
        id: "lkjfhdvdyhdzi",
        name: "Greg"
    },
    {
        email: "julie@gmail.com",
        id: "mpnwsaezert",
        name: "Julie"
    },
    {
        email: "michel@gmail.com",
        id: "nhyioaqstr",
        name: "Michel"
    },
    {
        email: "rahff@gmail.com",
        id: "azertyuiopp",
        name: "Rahff"
    },
    {
        email: "mikki@gmail.com",
        id: "mlkjhgfdfghjk",
        name: "Mikki"
    }
]


export const newTeammate: Teammate = {
    email: "newguys@gmail.com",
    name: "Gerard",
    id: "teroijdppedidhh"
}