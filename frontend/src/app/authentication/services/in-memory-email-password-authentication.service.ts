import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { fakeAuthentication } from 'src/core/application/security/data/authentication.data';
import { EmailPasswordCredentials, Authentication, SignupUserRequest, TokenJwtPair } from 'src/core/application/security/dto/Authentication';
import { InvalidTokenException, TokenExpiresException } from 'src/core/application/security/exceptions/authentication-exceptions';
import { EmailPasswordGateway, TokenGateway } from 'src/core/application/security/spi/AuthenticationGateway';

@Injectable({
  providedIn: 'root'
})
export class InMemoryEmailPasswordAuthentication implements EmailPasswordGateway {

  constructor() { }

  public authenticate(credentials: EmailPasswordCredentials): Observable<Authentication> {
    if(credentials.email === "validemail@gmail.com" && credentials.password === "correctPassword"){
      return new Observable((observable) => {
        observable.next(fakeAuthentication);
      })
    }else return throwError(() => new Error("bad credentials"));
  }

  public signup(request: SignupUserRequest): Observable<Authentication> {
    if(request.email === "validemail@gmail.com"){
      return new Observable((observable) => {
        observable.next(fakeAuthentication);
      })
    }else return throwError(() => new Error("email already exists"));
  }

 
}

@Injectable({
  providedIn: "root"
})
export class InMemoryTokenAuthentication implements TokenGateway {

  private localAuthentication: Authentication | null = fakeAuthentication;

  public constructor(){}

  public setAuthentication(authentication: Authentication | null): void {
    this.localAuthentication = authentication;
  }

  public authenticate(): Observable<Authentication> {
  
    const token = this.localAuthentication?.token;
    if(!token) return throwError(() => new Error("no token"));
    if(token.accessToken === "expires token") return throwError(()=> new TokenExpiresException(token.refreshToken))
    if(token.accessToken === fakeAuthentication.token.accessToken) {
      return new Observable((observable) => {
        observable.next(fakeAuthentication);
      })
    }else return throwError(() => new InvalidTokenException());
  }

  public refreshToken(token: string): Observable<Authentication> {
    if(token === this.localAuthentication?.token.refreshToken)
    return new Observable((observable) => {
      observable.next(fakeAuthentication);
    })
    else return throwError(() => new InvalidTokenException());
  }
  
} 

