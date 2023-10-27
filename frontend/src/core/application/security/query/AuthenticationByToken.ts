import { Observable, catchError, map } from "rxjs";
import { Query } from "../../shared/query/Query";
import { Authentication } from "../dto/Authentication";
import { AuthenticationGateway } from "../spi/AuthenticationGateway";
import { Result } from "../../shared/dto/Result";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";
import { TokenExpiresException } from "../exceptions/authentication-exceptions";



export class AuthenticationByToken extends Query<Authentication> {

    public constructor(private authenticationGateway: AuthenticationGateway,
                       private userContextHolder: UserContextHolder){super()}

    public authenticate(): Observable<Result<Authentication>> {
        const token = this.userContextHolder.getToken();
        return this.authenticationGateway.authenticateByToken(token)
        .pipe(map(this.onSuccess),catchError(this.handleError.bind(this)))
        
    }

    private handleError(error: Error): Observable<Result<Authentication>> {
        if(this.needRefreshToken(error)) return this.refreshToken(error.refreshToken);
        return this.onError(error);
    }

    private needRefreshToken(error: Error): error is TokenExpiresException  {
        return error instanceof TokenExpiresException;
    }

    private refreshToken(token: string): Observable<Result<Authentication>> {
        return this.authenticationGateway.refreshToken(token)
            .pipe(map(this.onSuccess),
             catchError(this.onError))
    }
}

