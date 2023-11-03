import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupViewRoutingModule } from './signup-view-routing.module';
import { SignupUser } from 'src/core/application/security/command/SignupUser';
import { AuthenticationGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryEmailPasswordAuthentication } from '../../services/in-memory-email-password-authentication.service';
import { FakeUserContextHolder } from 'src/app/dashboard/services/inMemory/FakeUserContextHolder';



@NgModule({
  declarations: [
    ...SignupViewRoutingModule.viewComponent,
  ],
  imports: [
    CommonModule,
    SignupViewRoutingModule
  ],
  providers: [
    {
      provide: SignupUser, 
      useFactory: (a: AuthenticationGateway, u: UserContextHolder) => new SignupUser(a, u),
      deps: [InMemoryEmailPasswordAuthentication, FakeUserContextHolder]
    }
  ]
})
export class SignupViewModule { }
