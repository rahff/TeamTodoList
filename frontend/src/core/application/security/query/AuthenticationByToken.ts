import { Observable, catchError, of, switchMap } from "rxjs";
import { Query } from "../../shared/query/Query";
import { Authentication } from "../dto/Authentication";
import { TokenGateway } from "../spi/AuthenticationGateway";
import { Result } from "../../shared/dto/Result";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";
import { TokenExpiresException } from "../exceptions/authentication-exceptions";



export class AuthenticationByToken extends Query<Authentication> {

    public constructor(private authenticationGateway: TokenGateway,
                       private userContextHolder: UserContextHolder){super()}

    public authenticate(): Observable<Result<Authentication>> {
        return this.authenticationGateway.authenticate()
        .pipe(switchMap(this.onSuccesResult.bind(this)), catchError(this.handleError.bind(this)));
    }

    private onSuccesResult(authentication: Authentication): Observable<Result<Authentication>> {
        this.userContextHolder.saveAuthentication(authentication);
        return of(this.onSuccess(authentication));
    }

    private handleError(error: Error): Observable<Result<Authentication>> {
        if(this.needRefreshToken(error)) return this.refreshToken(error.refreshToken);
        return this.onError(error);
    }

    private needRefreshToken(error: Error): error is TokenExpiresException  {
        return error instanceof TokenExpiresException;
    }

    public refreshToken(token: string): Observable<Result<Authentication>> {
        return this.authenticationGateway.refreshToken(token)
            .pipe(switchMap(this.onSuccesResult.bind(this)),
             catchError(this.onError))
    }
}

