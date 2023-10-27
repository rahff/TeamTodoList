import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeammateListViewComponent } from './teammate-list-view.component';

const routes: Routes = [
  {
    path: "", component: TeammateListViewComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TeammateListViewRoutingModule {
  public static viewComponents = [TeammateListViewComponent]
}
