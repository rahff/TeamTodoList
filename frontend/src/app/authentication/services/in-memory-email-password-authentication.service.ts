import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { fakeAuthentication } from 'src/core/application/security/data/authentication.data';
import { EmailPasswordCredentials, Authentication, SignupUserRequest, TokenJwtPair } from 'src/core/application/security/dto/Authentication';
import { InvalidTokenException, TokenExpiresException } from 'src/core/application/security/exceptions/authentication-exceptions';
import { EmailPasswordGateway, TokenGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import {BrowserContextService} from "../../dashboard/services/shared/browser-context.service";

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

