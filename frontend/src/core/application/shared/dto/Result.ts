

export class Result<T, E=any> {

    private constructor(private error: E | null, private value: T | null ){}
    
    public static create<T, E>(value: T, error: E) {
        return new Result(error, value);
    }

    public isOk(): boolean {
        return !this.error && !!this.value;
    }

    public getError(): E   {
        if(!this.error) throw "no error"
        return this.error;
    }

    public getValue(): T  {
        if(!this.value) throw "no value"
        return this.value;
    }
}