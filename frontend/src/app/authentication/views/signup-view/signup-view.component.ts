import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import { SignupUser } from 'src/core/application/security/command/SignupUser';
import { Authentication } from 'src/core/application/security/dto/Authentication';
import { Result } from 'src/core/application/shared/dto/Result';

@Component({
  selector: 'app-signup-view',
  templateUrl: './signup-view.component.html',
  styleUrls: ['./signup-view.component.css']
})
export class SignupViewComponent implements OnInit {

  public signupForm!: FormGroup;
  public errorMessage: string | null = null

  constructor(private formBuilder: FormBuilder, 
              private signupUser: SignupUser,
              private router: Router) { }

  public ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      name: ["", [Validators.required, Validators.minLength(2), Validators.maxLength(45)]],
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required]],
      confirm: ["", [Validators.required]],
    })
  }

  public onSubmit(): void {
    if(this.signupForm.valid){
      this.signupUser.execute(this.signupForm.getRawValue())
      .pipe(first()).subscribe({
        next: this.onSignupResult.bind(this)
      })
    }
  }

  private onSignupResult(result: Result<Authentication, Error>): void {
    if(result.isOk()) this.router.navigateByUrl("/dashboard");
    else this.errorMessage = result.getError().message
  }
}
