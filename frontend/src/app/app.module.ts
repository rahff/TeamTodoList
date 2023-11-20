import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import { TokenGateway } from 'src/core/application/security/spi/AuthenticationGateway';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryTokenAuthentication } from './authentication/services/in-memory-email-password-authentication.service';
import { FakeUserContextHolder } from './dashboard/services/inMemory/FakeUserContextHolder';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
