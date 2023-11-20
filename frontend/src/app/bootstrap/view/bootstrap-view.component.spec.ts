import { Router } from '@angular/router';
import { BootstrapViewComponent } from './bootstrap-view.component';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import {  InMemoryTokenAuthentication } from 'src/app/authentication/services/in-memory-email-password-authentication.service';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { fakeAuthentication, fakeAuthenticationWithExpiredAccessToken, fakeAuthenticationWithInvalidToken } from 'src/core/application/security/data/authentication.data';
import { Authentication } from 'src/core/application/security/dto/Authentication';



describe('BootstrapComponent', () => {
  let component: BootstrapViewComponent;
  let router: jasmine.SpyObj<Router>;
  let authenticationByToken: AuthenticationByToken;
  let userContextHolder: jasmine.SpyObj<UserContextHolder>
  const setupSecurityContextHolder = (authentication:Authentication | null) => {
    const tokenAuthenticator = new InMemoryTokenAuthentication();
    tokenAuthenticator.setAuthentication(authentication);
    authenticationByToken = new AuthenticationByToken(tokenAuthenticator, userContextHolder);
    component = new BootstrapViewComponent(authenticationByToken, router);
  }
  beforeEach(() => {
    userContextHolder = jasmine.createSpyObj("UserContextHolder", ["saveAuthentication"]);
    router = jasmine.createSpyObj("Router", ["navigateByUrl"]);
  });

  it('should redirect on login view when there is not token at local storage', () => {
    setupSecurityContextHolder(null);
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/auth/login");
  });

  it('should redirect on login view when there is an invalid token at local storage', () => {
    setupSecurityContextHolder(fakeAuthenticationWithInvalidToken);
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/auth/login");
  });

  it('should redirect on dashboard view when there is valid token at local storage', () => {
    setupSecurityContextHolder(fakeAuthentication);
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/dashboard");
  });

  it('should refresh expire token then redirect on dashboard view', () => {
    setupSecurityContextHolder(fakeAuthenticationWithExpiredAccessToken);
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/dashboard");
  });
});
