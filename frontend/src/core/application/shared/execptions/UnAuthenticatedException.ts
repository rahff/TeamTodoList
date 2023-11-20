export class UnAuthenticatedException extends Error {
    constructor(){super("must be authenticated")}
}