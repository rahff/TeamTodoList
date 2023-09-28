import { Observable, of } from "rxjs";
import { Result } from "../dto/Result";

export abstract class Command {

    public abstract execute(request: any): Observable<Result>;

    protected onSuccess(): Result {
        return Result.ok();
    }

    protected onError(error: any): Observable<Result> {
        return of(Result.withErrorMesage(error.message));
    }
}