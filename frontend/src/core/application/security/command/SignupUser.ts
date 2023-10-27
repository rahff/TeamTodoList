import { Observable, catchError, map, of, switchMap, tap } from "rxjs";
import { Command } from "../../shared/command/Command";
import { Result } from "../../shared/dto/Result";
import { Authentication, SignupUserRequest } from "../dto/Authentication";
import { AuthenticationGateway } from "../spi/AuthenticationGateway";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";



export class SignupUser extends Command<Authentication, SignupUserRequest> {
    
    public constructor(private authenticationGateway: AuthenticationGateway,
                       private userContextHolder: UserContextHolder){super()}


    public override execute(request: SignupUserRequest): Observable<Result<Authentication>> {
        return this.authenticationGateway.signup(request)
        .pipe(switchMap(this.onSuccesResult.bind(this)),
              catchError(this.onError));
    }

    private onSuccesResult(authentication: Authentication): Observable<Result<Authentication>> {
        this.userContextHolder.save(authentication);
        return of(this.onSuccess(authentication));
     }
}