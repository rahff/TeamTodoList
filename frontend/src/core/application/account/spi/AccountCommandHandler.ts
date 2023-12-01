import {ChangePasswordRequest} from "../dto/ChangePasswordRequest";
import {Observable} from "rxjs";
import {Message} from "../../shared/dto/Message";

export interface AccountCommandHandler {
    changePassword(request: ChangePasswordRequest): Observable<Message>;
}