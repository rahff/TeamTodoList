import {Injectable} from "@angular/core";
import {TokenGateway} from "../../../core/application/security/spi/AuthenticationGateway";
import {Authentication} from "../../../core/application/security/dto/Authentication";
import {Observable, throwError} from "rxjs";
import {
    InvalidTokenException,
    TokenExpiresException
} from "../../../core/application/security/exceptions/authentication-exceptions";
import {fakeAuthentication} from "../../../core/application/security/data/authentication.data";
import {BrowserContextService} from "../../dashboard/services/shared/browser-context.service";

@Injectable({
    providedIn: "root"
})
export class FakeTokenAuthentication implements TokenGateway {

    private localAuthentication: Authentication | null = null;

    public constructor(){}

    public setAuthentication(authentication: Authentication | null): void {
        this.localAuthentication = authentication;
    }

    public authenticate(): Observable<Authentication> {

        const token = this.localAuthentication?.token;
        if(!token) return throwError(() => new Error("no token"));
        if(token.accessToken === "expires token") return throwError(()=> new TokenExpiresException(token.refreshToken))
        if(token.accessToken === fakeAuthentication.token.accessToken) {
            return new Observable((observable) => {
                observable.next(fakeAuthentication);
            })
        }else return throwError(() => new InvalidTokenException());
    }

    public refreshToken(token: string): Observable<Authentication> {
        if(token === this.localAuthentication?.token.refreshToken)
            return new Observable((observable) => {
                observable.next(fakeAuthentication);
            })
        else return throwError(() => new InvalidTokenException());
    }

}

@Injectable({
    providedIn: "root"
})
export class InMemoryTokenAuthentication implements TokenGateway {

    private localAuthentication: Authentication | null = null;

    public constructor(private userContextHolder: BrowserContextService ){}

    public authenticate(): Observable<Authentication> {

        const token = this.userContextHolder.getToken();
        if(!token) return throwError(() => new Error("no token"));
        if(token.accessToken === "expires token") return throwError(()=> new TokenExpiresException(token.refreshToken))
        if(token.accessToken === fakeAuthentication.token.accessToken) {
            return new Observable((observable) => {
                observable.next(fakeAuthentication);
            })
        }else return throwError(() => new InvalidTokenException());
    }

    public refreshToken(token: string): Observable<Authentication> {
        if(token === this.localAuthentication?.token.refreshToken)
            return new Observable((observable) => {
                observable.next(fakeAuthentication);
            })
        else return throwError(() => new InvalidTokenException());
    }

}

