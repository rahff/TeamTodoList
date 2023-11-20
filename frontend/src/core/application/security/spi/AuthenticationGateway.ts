import { Observable } from "rxjs";
import { Authentication, EmailPasswordCredentials, SignupUserRequest, TokenJwtPair } from "../dto/Authentication";


export interface TokenGateway {
    authenticate(): Observable<Authentication>;
    refreshToken(token: string): Observable<Authentication>;

}

export interface EmailPasswordGateway {
    authenticate(credentials: EmailPasswordCredentials): Observable<Authentication>;
    signup(request: SignupUserRequest): Observable<Authentication>;
}