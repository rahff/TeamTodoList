import { Observable, of } from "rxjs";
import { Result } from "../dto/Result";

export abstract class Command<TReturn, TRequest> {

    public abstract execute(request: TRequest): Observable<Result<TReturn>>;

    protected onSuccess(value: TReturn): Result<TReturn> {
        return Result.create<TReturn, null>(value, null);
    }

    protected onError(error: Error): Observable<Result<TReturn>> {
        return of(Result.create<any, Error>(null, error));
    }
}