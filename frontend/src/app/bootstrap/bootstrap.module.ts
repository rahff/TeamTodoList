import { NgModule } from '@angular/core';
import { BootstrapRoutingModule } from './bootstrap-routing.module';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import { TokenGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryTokenAuthentication } from '../authentication/services/in-memory-email-password-authentication.service';
import { FakeUserContextHolder } from '../dashboard/services/inMemory/FakeUserContextHolder';




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
      deps: [InMemoryTokenAuthentication, FakeUserContextHolder]
    },
  ]
})
export class BootstrapModule { }
