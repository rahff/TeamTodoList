import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeamListViewComponent } from './team-list-view.component';

const routes: Routes = [
  {
    path: "", component: TeamListViewComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TeamListViewRoutingModule {
  public static viewComponents = [TeamListViewComponent]
}
