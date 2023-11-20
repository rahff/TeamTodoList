import { Authentication } from "../dto/Authentication";

export const fakeAuthentication: Authentication = {
    user: {
        name: "Rahff",
        email: "rahff@gmail.com",
        accountId: "123",
        role: "manager",
        id: "userId123"
    },
    token: { accessToken: 
        "azertyuioppmlkjhgfsdfghjklmpoiudfghjklmlkjhgdfghj",
        refreshToken: "teyujdjjdjdjjjjjjjjjjjjjjjjjjjjjjjd"
    }
}

export const fakeAuthenticationWithInvalidToken: Authentication = {
    user: {
        name: "Rahff",
        email: "rahff@gmail.com",
        accountId: "123",
        role: "manager",
        id: "userId123"
    },
    token: { accessToken: 
        "invalid token",
        refreshToken: "invalid token"
    }
}

export const fakeAuthenticationWithExpiredAccessToken: Authentication = {
    user: {
        name: "Rahff",
        email: "rahff@gmail.com",
        accountId: "123",
        role: "manager",
        id: "userId123"
    },
    token: { accessToken: 
        "expires token",
        refreshToken: "resfresh token"
    }
}