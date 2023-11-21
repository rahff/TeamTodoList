import { Observable, catchError, of, switchMap } from "rxjs";
import { Command } from "../../shared/command/Command";
import { Result } from "../../shared/dto/Result";
import { Authentication, SignupUserRequest } from "../dto/Authentication";
import { EmailPasswordGateway } from "../spi/AuthenticationGateway";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";



export class SignupUser extends Command<Authentication, SignupUserRequest> {
    
    public constructor(private authenticationGateway: EmailPasswordGateway,
                       private userContextHolder: UserContextHolder){super()}


    public override execute(request: SignupUserRequest): Observable<Result<Authentication>> {
        return this.authenticationGateway.signup(request)
        .pipe(switchMap(this.onSuccessResult.bind(this)),
              catchError(this.onError));
    }

    private onSuccessResult(authentication: Authentication): Observable<Result<Authentication>> {
        this.userContextHolder.saveAuthentication(authentication);
        return of(this.onSuccess(authentication));
     }
}