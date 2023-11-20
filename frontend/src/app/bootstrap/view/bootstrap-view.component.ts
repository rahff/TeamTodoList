import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Authentication } from 'src/core/application/security/dto/Authentication';
import { AuthenticationByToken } from 'src/core/application/security/query/AuthenticationByToken';
import { Result } from 'src/core/application/shared/dto/Result';

@Component({
  selector: 'app-bootstrap',
  templateUrl: './bootstrap-view.component.html',
  styleUrls: ['./bootstrap-view.component.css']
})
export class BootstrapViewComponent implements OnInit, OnDestroy {

  private subscribtion!: Subscription;

  constructor(private authenticationByToken: AuthenticationByToken, private router: Router) { }

  public ngOnInit(): void {
    this.subscribtion = this.authenticationByToken.authenticate().subscribe({
      next: this.onAuthenticationResult.bind(this)
    })
  }

  private onAuthenticationResult(result: Result<Authentication>): void {
    if(result.isOk()) this.router.navigateByUrl("/dashboard");
    else this.router.navigateByUrl("/auth/login");
  }

  public ngOnDestroy(): void {
    this.subscribtion.unsubscribe();
  }

}
