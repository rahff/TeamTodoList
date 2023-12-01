import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupViewRoutingModule } from './signup-view-routing.module';
import { SignupUser } from 'src/core/application/security/command/SignupUser';
import { EmailPasswordGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryEmailPasswordAuthentication } from '../../services/in-memory-email-password-authentication.service';
import { FakeUserContextHolder } from 'src/app/dashboard/services/inMemory/FakeUserContextHolder';
import {ReactiveFormsModule} from "@angular/forms";
import {signupUserDependencyProvider} from "./dependencyProvider";
import {environment} from "../../../../environments/environment";



@NgModule({
  declarations: [
    ...SignupViewRoutingModule.viewComponent,
  ],
    imports: [
        CommonModule,
        SignupViewRoutingModule,
        ReactiveFormsModule
    ],
  providers: [
    {
      provide: SignupUser, 
      useFactory: (a: EmailPasswordGateway, u: UserContextHolder) => new SignupUser(a, u),
      deps: signupUserDependencyProvider(environment.dataSource)
    }
  ]
})
export class SignupViewModule { }
