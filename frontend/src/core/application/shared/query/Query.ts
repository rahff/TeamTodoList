import { Observable, of } from "rxjs";
import { Result } from "../dto/Result";

export abstract class Query<T> {

    protected onSuccess(value: T): Result<T> {
        return Result.create<T, null>(value, null);
    }

    protected onError(error: Error): Observable<Result<T>> {
        return of(Result.create<any, Error>(null, error));
    }
}