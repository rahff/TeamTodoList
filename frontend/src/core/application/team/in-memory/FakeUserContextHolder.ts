import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";

export class FakeUserContextHolder implements UserContextHolder {

    public getUserId(): string {
        return "userId";
    }


    public getAccountId(): string {
        return "123";
    }

}