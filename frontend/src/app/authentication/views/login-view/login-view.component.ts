import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import { LoginUser } from 'src/core/application/security/command/LoginUser';
import { Authentication } from 'src/core/application/security/dto/Authentication';
import { Result } from 'src/core/application/shared/dto/Result';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css']
})
export class LoginViewComponent implements OnInit {

  public loginForm!: FormGroup;
  public errorMessage: string | null = null;

  public constructor(private loginUser: LoginUser, 
                     private formBuilder: FormBuilder, 
                     private router: Router) {}

  public ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required, Validators.minLength(6)]],
    })
  }

  public onSubmit(): void {
    if(this.loginForm.valid){
      this.loginUser.execute(this.loginForm.getRawValue())
      .pipe(first()).subscribe({
        next: this.onLoginResult.bind(this)
      })
    }
  }

  private onLoginResult(result: Result<Authentication, Error>): void {
    if(result.isOk()) this.router.navigateByUrl("/dashboard");
    else this.errorMessage = result.getError().message;
  }
}
