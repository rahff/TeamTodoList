import { Router } from '@angular/router';
import { BootstrapViewComponent } from './bootstrap-view.component';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import { InMemoryEmailPasswordAuthentication } from 'src/app/authentication/services/in-memory-email-password-authentication.service';
import { FakeUserContextHolder } from 'src/app/dashboard/services/inMemory/FakeUserContextHolder';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { fakeAuthentication } from 'src/core/application/security/data/authentication.data';



describe('BootstrapComponent', () => {
  let component: BootstrapViewComponent;
  let router: jasmine.SpyObj<Router>;
  let authenticationByToken: AuthenticationByToken;
  let userContextHolder: jasmine.SpyObj<UserContextHolder>
  beforeEach(() => {
    userContextHolder = jasmine.createSpyObj("UserContextHolder", ["getToken"])
    authenticationByToken = new AuthenticationByToken(new InMemoryEmailPasswordAuthentication(), userContextHolder);
    router = jasmine.createSpyObj("Router", ["navigateByUrl"]);
    component = new BootstrapViewComponent(authenticationByToken, router);
  });

  it('should redirect on login view when there is not token at local storage', () => {
    userContextHolder.getToken.and.returnValue(null);
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/auth/login");
  });

  it('should redirect on login view when there is an invalid token at local storage', () => {
    userContextHolder.getToken.and.returnValue({accessToken: "invalid token", refreshToken: "invalid too"});
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/auth/login");
  });

  it('should redirect on dashboard view when there is valid token at local storage', () => {
    userContextHolder.getToken.and.returnValue({accessToken: fakeAuthentication.token.accessToken, refreshToken: "refreshToken"})
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/dashboard");
  });

  it('should refresh expire token then redirect on dashboard view', () => {
    userContextHolder.getToken.and.returnValue({accessToken: "expires token", refreshToken: "refreshToken"})
    component.ngOnInit();
    expect(router.navigateByUrl).toHaveBeenCalledWith("/dashboard");
  });
});
