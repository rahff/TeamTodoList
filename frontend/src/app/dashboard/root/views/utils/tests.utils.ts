import { tick } from "@angular/core/testing";
import { ActivatedRoute, ActivatedRouteSnapshot, ParamMap } from "@angular/router";

export const ngOnInitPast500Ms = (component: any) => {
    component.ngOnInit();
    tick(505);
}


class FakeActivatedRouteSnaphot extends ActivatedRouteSnapshot {

    public constructor(private param: string, private key: string){super()}
  
    public override get paramMap(): ParamMap {
      const param: ParamMap = {
        get: (name: string) => name === this.key ? this.param : null,
        getAll: (name: string) => name === this.key ? [this.param] : [],
        has: (name: string) => name === this.key,
        keys: [this.key]
      }
        return param;
    }
}

export class FakeActivatedRoute extends ActivatedRoute {
  
  public constructor(private param: string, private key: string){super()}

 public override snapshot: ActivatedRouteSnapshot = new FakeActivatedRouteSnaphot(this.param, this.key);
}