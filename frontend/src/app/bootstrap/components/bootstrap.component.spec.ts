import { Router } from '@angular/router';
import { BootstrapComponent } from './bootstrap.component';



describe('BootstrapComponent', () => {
  let component: BootstrapComponent;
  let router: jasmine.SpyObj<Router>
  beforeEach(() => {
    router = jasmine.createSpyObj("Router", ["navigateByUrl"]);
    component = new BootstrapComponent(router);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
