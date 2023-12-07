import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmailPasswordCredentials, Authentication, SignupUserRequest } from 'src/core/application/security/dto/Authentication';
import { EmailPasswordGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class EmailPasswordAuthentication implements EmailPasswordGateway {

  private baseServerUrl: string = environment.serverUrl;

  public constructor(private http: HttpClient) { }

  public authenticate(credentials: EmailPasswordCredentials): Observable<Authentication> {
    return this.http.post<Authentication>(this.baseServerUrl+"/login", credentials);

  }
}
