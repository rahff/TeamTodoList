export class UnAuthenticatedException extends Error {
    public constructor(){super("must be authenticated")}
}