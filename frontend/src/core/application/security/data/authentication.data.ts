import { Authentication } from "../dto/Authentication";

export const fakeAuthentication: Authentication = {
    user: {
        name: "Rahff",
        email: "rahff@gmail.com",
        accountId: "123",
        role: "manager"
    },
    token: { accessToken: 
        "azertyuioppmlkjhgfsdfghjklmpoiudfghjklmlkjhgdfghj",
        refreshToken: "teyujdjjdjdjjjjjjjjjjjjjjjjjjjjjjjd"
    }
}