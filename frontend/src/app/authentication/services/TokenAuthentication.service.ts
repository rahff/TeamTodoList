import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Authentication } from "src/core/application/security/dto/Authentication";
import { TokenGateway } from "src/core/application/security/spi/AuthenticationGateway";
import { environment } from "src/environments/environment";



@Injectable({
    providedIn: 'root'
  })
  export class TokenAuthentication implements TokenGateway {
  
    private baseserverUrl: string = environment.serverUrl;

    constructor(private http: HttpClient) { }

    public authenticate(): Observable<Authentication> {
        return this.http.get<Authentication>(this.baseserverUrl+"/authentication");
    }

    public refreshToken(refreshToken: string): Observable<Authentication> {
      const headers = new HttpHeaders({"Authorization": refreshToken});
      return this.http.get<Authentication>(this.baseserverUrl+"/refresh-token", {headers});
    }
  }
  