import { NgModule } from '@angular/core';
import { BootstrapRoutingModule } from './bootstrap-routing.module';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import { TokenGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import {AuthenticationByTokenDependencyProvider} from "./view/dependencyProvider";
import {environment} from "../../environments/environment";




@NgModule({
  declarations: [
    ...BootstrapRoutingModule.viewComponents
  ],
  imports: [
    BootstrapRoutingModule
  ],
  providers: [
    {
      provide: AuthenticationByToken, 
      useFactory: (gtw: TokenGateway, u: UserContextHolder) => new AuthenticationByToken(gtw, u),
      deps: AuthenticationByTokenDependencyProvider(environment.dataSource)
    },
  ]
})
export class BootstrapModule { }
