import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export class EqualsValidator {
    public static equals(control: AbstractControl): ValidatorFn {
        return (otherControl: AbstractControl): ValidationErrors | null => {
            if(control.value === otherControl?.value) return null;
            else return {"notMach": true}
        }
    }
}