import { Observable } from "rxjs";
import { Authentication, EmailPasswordCredentials, SignupUserRequest, TokenJwtPair } from "../dto/Authentication";

export interface AuthenticationGateway {
    login(credentials: EmailPasswordCredentials): Observable<Authentication>;
    signup(request: SignupUserRequest): Observable<Authentication>;
    authenticateByToken(token: TokenJwtPair | null): Observable<Authentication>;
    refreshToken(token: string):  Observable<Authentication>;
}
