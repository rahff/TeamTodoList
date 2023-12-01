import { Injectable } from '@angular/core';
import { IDProvider } from 'src/core/application/shared/interfaces/IDProvider';

@Injectable({
  providedIn: 'root'
})
export class UUIDService implements IDProvider {

  constructor() { }

  public generate(): string {
    return crypto.randomUUID();
  }
}
