import { NgModule } from '@angular/core';
import { LoginViewRoutingModule } from './login-view-routing.module';
import { LoginUser } from 'src/core/application/security/command/LoginUser';
import { EmailPasswordGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import {ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {loginUserDependencyProvider} from "./dependencyProvider";
import {environment} from "../../../../environments/environment";



@NgModule({
    declarations: [
    ...LoginViewRoutingModule.viewComponents,
    ],
    imports: [
        CommonModule,
        LoginViewRoutingModule,
        ReactiveFormsModule
    ],
    providers: [
        {
          provide: LoginUser,
          useFactory: (a: EmailPasswordGateway, u: UserContextHolder) => new LoginUser(a, u),
          deps: loginUserDependencyProvider(environment.dataSource)
        }
    ]
})
export class LoginViewModule { }
