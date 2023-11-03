import { NgModule } from '@angular/core';
import { BootstrapRoutingModule } from './bootstrap-routing.module';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import { AuthenticationGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryEmailPasswordAuthentication } from '../authentication/services/in-memory-email-password-authentication.service';
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
      useFactory: (gtw: AuthenticationGateway, u: UserContextHolder) => new AuthenticationByToken(gtw, u),
      deps: [InMemoryEmailPasswordAuthentication, FakeUserContextHolder]
    },
  ]
})
export class BootstrapModule { }
