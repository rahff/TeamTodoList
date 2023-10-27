import { Team } from "../../dto/Team";
import { Teammate } from "../../dto/Teammate";


export const newTeammate: Teammate = {
    email: "newguys@gmail.com",
    name: "Gerard",
    id: "teroijdppedidhh",
    teamId: null
}

export const team2 = {
    id: "xcvbtghrtyui",
    name: "Team2",
    teammates: [
        {
            email: "roger@gmail.com",
            id: "poijbvtgfmm",
            name: "Roger",
            teamId: "xcvbtghrtyui"
        },
        {
            email: "sarah@gmail.com",
            id: "mplokijuhygt",
            name: "Sarah",
            teamId: "xcvbtghrtyui"
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

export const team2AfterAddTeammate = {
    id: "xcvbtghrtyui",
    name: "Team2",
    teammates: [
        {
            email: "roger@gmail.com",
            id: "poijbvtgfmm",
            name: "Roger",
            teamId: "xcvbtghrtyui"
        },
        {
            email: "sarah@gmail.com",
            id: "mplokijuhygt",
            name: "Sarah",
            teamId: "xcvbtghrtyui"
        },
        {...newTeammate, teamId: "fghjklopiuyt"}     
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


export const team2AfterRemoveTeammate2 = {
    id: "xcvbtghrtyui",
    name: "Team2",
    teammates: [
        {
            email: "roger@gmail.com",
            id: "poijbvtgfmm",
            name: "Roger",
            teamId: "xcvbtghrtyui"
        }
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
                name: "Rahff",
                teamId: "fghjklopiuyt"
            },
            {
                email: "mikki@gmail.com",
                id: "mlkjhgfdfghjk",
                name: "Mikki",
                teamId: "fghjklopiuyt"
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
                name: "Julie",
                teamId: "iudfdshbbcvvdg"
            },
            {
                email: "michel@gmail.com",
                id: "nhyioaqstr",
                name: "Michel",
                teamId: "iudfdshbbcvvdg"
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
                name: "Rahff",
                teamId: "fghjklopiuyt"
            },
            {
                email: "mikki@gmail.com",
                id: "mlkjhgfdfghjk",
                name: "Mikki",
                teamId: "fghjklopiuyt"
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
                name: "Julie",
                teamId: "iudfdshbbcvvdg"
            },
            {
                email: "michel@gmail.com",
                id: "nhyioaqstr",
                name: "Michel",
                teamId: "iudfdshbbcvvdg"
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
                name: "Greg",
                teamId: "jsupmsdhbbcuyieue"
            },
            {
                email: "jacqueline@gmail.com",
                id: "mpetfxccxxqq",
                name: "Jacqueline",
                teamId: "jsupmsdhbbcuyieue"
            },
        ],
        todoLists: []
}

export const teammateList: Teammate[] = [
    {
        email: "greg@gmail.com",
        id: "lkjfhdvdyhdzi",
        name: "Greg",
        teamId: "fghjklopiuyt"
    },
    {
        email: "jacqueline@gmail.com",
        id: "mpetfxccxxqq",
        name: "Jacqueline",
        teamId: "fghjklopiuyt"
    },
    {
        email: "julie@gmail.com",
        id: "mpnwsaezert",
        name: "Julie",
        teamId: "xcvbtghrtyui"
    },
    {
        email: "michel@gmail.com",
        id: "nhyioaqstr",
        name: "Michel",
        teamId: "xcvbtghrtyui"
    },
    {
        email: "rahff@gmail.com",
        id: "azertyuiopp",
        name: "Rahff",
        teamId: "iudfdshbbcvvdg"
    },
    {
        email: "mikki@gmail.com",
        id: "mlkjhgfdfghjk",
        name: "Mikki",
        teamId: "iudfdshbbcvvdg"
    },
    {
        email: "bob@gmail.com",
        id: "ryzuakncgskjk",
        name: "Bob",
        teamId: null
    },
    {
        email: "sandra@gmail.com",
        id: "kncpmjjjuztt",
        name: "Sandra",
        teamId: null
    }
]

export const availableTeammates: Teammate[] = [
    {
        email: "bob@gmail.com",
        id: "ryzuakncgskjk",
        name: "Bob",
        teamId: null
    },
    {
        email: "sandra@gmail.com",
        id: "kncpmjjjuztt",
        name: "Sandra",
        teamId: null
    }
]


export const teammateListAfterFiredOne: Teammate[] = [
    {
        email: "greg@gmail.com",
        id: "lkjfhdvdyhdzi",
        name: "Greg",
        teamId: "fghjklopiuyt"
    },
    {
        email: "julie@gmail.com",
        id: "mpnwsaezert",
        name: "Julie",
        teamId: "xcvbtghrtyui"
    },
    {
        email: "michel@gmail.com",
        id: "nhyioaqstr",
        name: "Michel",
        teamId: "xcvbtghrtyui"
    },
    {
        email: "rahff@gmail.com",
        id: "azertyuiopp",
        name: "Rahff",
        teamId: "iudfdshbbcvvdg"
    },
    {
        email: "mikki@gmail.com",
        id: "mlkjhgfdfghjk",
        name: "Mikki",
        teamId: "iudfdshbbcvvdg"
    },
    {
        email: "bob@gmail.com",
        id: "ryzuakncgskjk",
        name: "Bob",
        teamId: null
    },
    {
        email: "sandra@gmail.com",
        id: "kncpmjjjuztt",
        name: "Sandra",
        teamId: null
    }
]


