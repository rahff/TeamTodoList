import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeammateListViewRoutingModule } from './teammate-list-view-routing.module';
import { SharedViewModule } from '../shared-view/shared-view.module';
import { TeammateListStoreApi } from 'src/core/store/teammate-list/TeammateListStoreApi';
import { store } from 'src/core/store/Store';
import { TeammateList } from 'src/core/application/team/query/TeammateList';
import { TeamQueryHandler } from 'src/core/application/team/spi/TeamQueryHandler';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryTeamQueryHandler } from '../../services/inMemory/InMemoryTeamQueryHandler';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';
import { AddTeammateFormComponent } from './components/add-teammate-form/add-teammate-form.component';



@NgModule({
  declarations: [
    ...TeammateListViewRoutingModule.viewComponents,
    AddTeammateFormComponent
  ],
  imports: [
    CommonModule,
    TeammateListViewRoutingModule,
    SharedViewModule
  ],
  providers: [
    {
      provide: TeammateListStoreApi, useFactory: () => new TeammateListStoreApi(store)
    },
    {
      provide: TeammateList, useFactory: (qh: TeamQueryHandler, uch: UserContextHolder) => new TeammateList(qh, uch),
      deps: [InMemoryTeamQueryHandler, FakeUserContextHolder]
    },
  ]
})
export class TeammateListViewModule { }
