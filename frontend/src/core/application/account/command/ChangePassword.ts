import {catchError, map, Observable, of} from "rxjs";
import {Command} from "../../shared/command/Command";
import {Result} from "../../shared/dto/Result";
import {ChangePasswordRequest} from "../dto/ChangePasswordRequest";
import {AccountCommandHandler} from "../spi/AccountCommandHandler";
import {UserContextHolder} from "../../shared/interfaces/UserContextHolder";
import {ChangePasswordFormData} from "../dto/ChangePasswordFormData";
import {UnAuthenticatedException} from "../../shared/execptions/UnAuthenticatedException";
import {Message} from "../../shared/dto/Message";

export class ChangePassword extends Command<Message, ChangePasswordFormData> {

    public constructor(private accountCommandHandler: AccountCommandHandler, private userContextHolder: UserContextHolder) { super() }

    public execute(formData: ChangePasswordFormData): Observable<Result<Message>> {
        const userId = this.userContextHolder.getUserId();
        if(!userId) return this.onError(new UnAuthenticatedException());
        const request: ChangePasswordRequest = {...formData, userId}
        return this.accountCommandHandler.changePassword(request)
            .pipe(map(this.onSuccess), catchError(this.onError));
    }

}