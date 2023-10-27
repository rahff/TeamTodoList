import { Injectable } from "@angular/core";
import { UserContextHolder } from "../../../../core/application/shared/interfaces/UserContextHolder";
import { Authentication } from "src/core/application/security/dto/Authentication";




@Injectable({
    providedIn: "root"
})
export class FakeUserContextHolder implements UserContextHolder {
    

    private context!: Map<string, Authentication>

    public constructor(){
        this.context = new Map<string, Authentication>();
    }

    public getUserId(): string {
        return "userId";
    }


    public getAccountId(): string {
        return "123";
    }

    public save(authentication: Authentication): void {
        this.context.set("authentication", authentication)
    }

    public getToken(): {accessToken: string, refreshToken: string} | null {
        return this.context.get("authentication")?.token || null
    }

    public haveBeenSaved(key: string, authentication: Authentication): boolean {
        return this.context.get(key)?.token === authentication.token
    }

}