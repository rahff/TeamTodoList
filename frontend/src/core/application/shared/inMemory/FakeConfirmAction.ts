import { Observable, observable } from "rxjs";
import { ConfirmationService } from "../command/ConfirmationService";

export class FakeConfirmAction<R> implements ConfirmationService<R> {

    askConfirmation(request: R): Observable<R> {
        return new Observable((observable) => {
            setTimeout(() => {
                observable.next(request)
            }, 1000);
        })
    }

}