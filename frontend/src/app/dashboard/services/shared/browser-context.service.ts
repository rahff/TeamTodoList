import { Injectable } from '@angular/core';
import { Authentication, TokenJwtPair } from 'src/core/application/security/dto/Authentication';
import { User } from 'src/core/application/security/dto/User';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';

@Injectable({
  providedIn: 'root'
})
export class BrowserContextService implements UserContextHolder {

  private AUTHENTICATION_KEY = "authentication";
  
  public constructor() {}

  public getAccountId(): string | null {
    try {
      const localUser =  this.getLocalUser();
      return localUser.accountId;
    } catch (error: any) {
      return null;
    }
  }

  public getUserId(): string | null {
    try {
      const localUser = this.getLocalUser();
      return localUser.id;
    } catch (error: any) {
      return null;
    }
  }

  public getToken(): TokenJwtPair | null {
    try {
      const authentication = this.getLocalAuthentication();
      return { 
        accessToken: authentication.token.accessToken, 
        refreshToken: authentication.token.refreshToken
      };
    } catch (error) {
      return null;
    }
  }

  private getLocalUser(): User {
    const authentication = this.getLocalAuthentication();
    return authentication.user;
  }

  private getLocalAuthentication(): Authentication {
    const authenticationJson = localStorage.getItem(this.AUTHENTICATION_KEY);
    if(!authenticationJson) throw new Error("no authentication");
    return JSON.parse(authenticationJson);
  }

  public saveAuthentication(authentication: Authentication): void {
    localStorage.removeItem(this.AUTHENTICATION_KEY);
    localStorage.setItem(this.AUTHENTICATION_KEY, JSON.stringify(authentication));
  }
}
