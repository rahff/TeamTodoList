import { tick } from "@angular/core/testing";
import { ActivatedRouteSnapshot, ParamMap } from "@angular/router";

export const ngOnInitPast500Ms = (component: any) => {
    component.ngOnInit();
    tick(505);
}


export class FakeActivatedRouteSnaphot extends ActivatedRouteSnapshot {

    public constructor(private param: string, private key: string){super()}
  
    public override get paramMap(): ParamMap {
      const param: ParamMap = {
        get: (name: string) => name === this.param ? this.param : null,
        getAll: (name: string) => name === this.param ? [this.param] : [],
        has: (name: string) => name === this.param,
        keys: [this.key]
      }
        return param;
    }
  }