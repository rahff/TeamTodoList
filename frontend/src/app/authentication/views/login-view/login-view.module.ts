import { NgModule } from '@angular/core';
import { LoginViewRoutingModule } from './login-view-routing.module';
import { LoginUser } from 'src/core/application/security/command/LoginUser';
import { AuthenticationGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryEmailPasswordAuthentication } from '../../services/in-memory-email-password-authentication.service';
import { FakeUserContextHolder } from 'src/app/dashboard/services/inMemory/FakeUserContextHolder';



@NgModule({
  declarations: [
    ...LoginViewRoutingModule.viewComponents,
  ],
  imports: [
    LoginViewRoutingModule
  ],
  providers: [
    {
      provide: LoginUser, 
      useFactory: (a: AuthenticationGateway, u: UserContextHolder) => new LoginUser(a, u),
      deps: [InMemoryEmailPasswordAuthentication, FakeUserContextHolder]
    }
  ]
})
export class LoginViewModule { }
