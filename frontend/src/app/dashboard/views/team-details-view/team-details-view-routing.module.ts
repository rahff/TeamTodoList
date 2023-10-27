import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeamDetailsViewComponent } from './team-details-view.component';

const routes: Routes = [
  {
    path: ":id", component: TeamDetailsViewComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TeamDetailsViewRoutingModule {
  public static viewComponents = [TeamDetailsViewComponent]
}
