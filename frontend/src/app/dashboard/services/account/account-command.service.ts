import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AccountCommandHandler} from "../../../../core/application/account/spi/AccountCommandHandler";
import { Observable } from 'rxjs';
import { ChangePasswordRequest } from 'src/core/application/account/dto/ChangePasswordRequest';
import { Message } from 'src/core/application/shared/dto/Message';
import {environment} from "../../../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class AccountCommandService implements AccountCommandHandler {

  private baseServerUrl: string = environment.serverUrl;
  public constructor(private http: HttpClient) {}

  public changePassword(request: ChangePasswordRequest): Observable<Message> {
    return this.http.put<Message>(this.baseServerUrl+"/change-password", request);
  }
}
