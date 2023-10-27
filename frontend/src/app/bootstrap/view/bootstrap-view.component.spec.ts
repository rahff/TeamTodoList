import { Router } from '@angular/router';
import { BootstrapViewComponent } from './bootstrap-view.component';



describe('BootstrapComponent', () => {
  let component: BootstrapViewComponent;
  let router: jasmine.SpyObj<Router>
  beforeEach(() => {
    router = jasmine.createSpyObj("Router", ["navigateByUrl"]);
    component = new BootstrapViewComponent(router);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
