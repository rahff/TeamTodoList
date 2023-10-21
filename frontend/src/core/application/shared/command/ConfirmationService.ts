import { Observable } from "rxjs";

export interface ConfirmationService<R> {
    askConfirmation(request: R): Observable<R>
}