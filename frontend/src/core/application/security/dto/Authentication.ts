import { User } from "./User"

export type Authentication = {
    user: User,
    token: TokenJwtPair
}

export type TokenJwtPair = {
    accessToken: string,
    refreshToken: string
}

export type  EmailPasswordCredentials = {
    email: string,
    password: string
}

export type  SignupUserRequest = {
    name: string,
    email: string,
    password: string
}