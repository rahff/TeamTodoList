import {Injectable} from "@angular/core";
import {Observable, of, throwError} from "rxjs";
import { ChangePasswordRequest } from "src/core/application/account/dto/ChangePasswordRequest";
import {AccountCommandHandler} from "../../../../core/application/account/spi/AccountCommandHandler";
import {UnAuthenticatedException} from "../../../../core/application/shared/execptions/UnAuthenticatedException";
import {Message} from "../../../../core/application/shared/dto/Message";


@Injectable({
    providedIn: "root"
})
export class InMemoryAccountCommandService implements AccountCommandHandler {
    public changePassword(request: ChangePasswordRequest): Observable<Message> {
        if (!request.userId) return throwError(() => new UnAuthenticatedException());
        if(request.oldPassword === "incorrectPassword") return throwError(() => new Error("invalid password"));
        return of({flag: true, text: "Ok"});
    }
}