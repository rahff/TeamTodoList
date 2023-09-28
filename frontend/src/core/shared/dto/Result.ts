export class Result {

    private constructor(private errorMessage: string | null ){}

    public static ok(): Result {
        return new Result(null);
    }

    public static withErrorMesage(message: string): Result {
        return new Result(message);
    }

    public isOk(): boolean {
        return !this.errorMessage;
    }

    public isError(): string {
        return this.errorMessage as string
    }
}