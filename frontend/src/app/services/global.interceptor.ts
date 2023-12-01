import { Inject, Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { BrowserContextService } from '../dashboard/services/shared/browser-context.service';
import { Authentication } from 'src/core/application/security/dto/Authentication';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import { Result } from 'src/core/application/shared/dto/Result';


@Injectable()
export class GlobalInterceptor implements HttpInterceptor {

  public constructor(private userContextHolder: BrowserContextService, 
                     private authentication: AuthenticationByToken) {}

  public intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.userContextHolder.getToken();
    if(!token || request.url === "/refresh-token") return next.handle(request);
    const requestWithToken = this.addTokenOnRequest(request, token.accessToken);
    return next.handle(requestWithToken).pipe(catchError((err) => this.onError(err, next, requestWithToken)));
  }

  private addTokenOnRequest(request: HttpRequest<unknown>, token: string): HttpRequest<unknown> {
    return request.clone({setHeaders: {Authorization: token}});
  }

  private onError(err: HttpErrorResponse, httpHandler: HttpHandler, request: HttpRequest<unknown>): Observable<HttpEvent<any>> {
    const token = this.userContextHolder.getToken();
    if(err.status == 401 && token){
      this.authentication.refreshToken(token.refreshToken)
      .pipe(switchMap((result: Result<Authentication>) => {
        if(result.isOk()){
          const accessToken = result.getValue().token.accessToken;
          const requestWithToken = this.addTokenOnRequest(request, accessToken)
          return httpHandler.handle(requestWithToken);
        }
        return throwError(()=> new Error(result.getError().message));
      }))
    }
    return throwError(()=> new Error(err.message));
  } 
}
