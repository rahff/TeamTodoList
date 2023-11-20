export class TokenExpiresException extends Error {
    constructor(public refreshToken: string){super("token expires")}
}

export class InvalidTokenException extends Error {
    constructor(){super("invalid token")}
}

export class BadcredentialsException extends Error {}
