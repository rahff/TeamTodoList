import { Observable, catchError, map, of, switchMap, tap } from "rxjs";
import { Command } from "../../shared/command/Command";
import { Result } from "../../shared/dto/Result";
import { Authentication, EmailPasswordCredentials } from "../dto/Authentication";
import { EmailPasswordGateway } from "../spi/AuthenticationGateway";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";

export class LoginUser extends Command<Authentication, EmailPasswordCredentials> {
    
    public constructor(private authenticationGateway: EmailPasswordGateway,
                       private userContextHolder: UserContextHolder){super()}


    public override execute(request: EmailPasswordCredentials): Observable<Result<Authentication>> {
        return this.authenticationGateway.authenticate(request)
        .pipe(switchMap(this.onSuccesResult.bind(this)),
              catchError(this.onError))
    }

    private onSuccesResult(authentication: Authentication): Observable<Result<Authentication>> {
       this.userContextHolder.saveAuthentication(authentication);
       return of(this.onSuccess(authentication));
    }
}