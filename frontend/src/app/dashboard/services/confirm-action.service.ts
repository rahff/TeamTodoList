import { Injectable } from '@angular/core';
import { Observable, catchError, first, from, map, of, switchMap, throwError } from 'rxjs';
import { Command } from 'src/core/application/shared/command/Command';
import { Result } from 'src/core/application/shared/dto/Result';
import Swal, { SweetAlertOptions, SweetAlertResult } from 'sweetalert2'



@Injectable({
  providedIn: 'root'
})
export class ConfirmActionService {

  private popUpEngine = Swal.mixin({
    customClass: {
      confirmButton: 'btn btn-blue',
      cancelButton: 'btn btn-red'
    },
    buttonsStyling: false
  })

  private popUpConfig: SweetAlertOptions = {
    title: 'Are you sure?',
    text: "You won't be able to revert this!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'No, cancel!',
    reverseButtons: true
  }

  constructor() {}

  public askConfirmationFor<T, R>(command: Command<T, R>, request: R): Observable<Result<T>> {
    return from(this.popUpEngine.fire(this.popUpConfig)).pipe(map((result: SweetAlertResult) => {
      if(result.isConfirmed) return request;
      else throw new Error("dismissed");
    })).pipe(switchMap(command.execute.bind(command)),
    catchError((err: any) => of(Result.create<any, any>(null, err))))
  }
}
