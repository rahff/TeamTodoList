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
import { JoinTeammate } from 'src/core/application/team/command/JoinTeammate';
import { TeamCommandHandler } from 'src/core/application/team/spi/TeamCommandHandler';
import { IDProvider } from 'src/core/application/shared/interfaces/IDProvider';
import { InMemoryTeamCommandHandler } from '../../services/inMemory/InMemoryTeamCommandHandler';
import { UUIDService } from '../../services/uuid.service';
import {JoinTeammateDependencyProvider, TeammateListDependencyProvider} from "./dependencyProvider";
import {environment} from "../../../../environments/environment";



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
      deps: TeammateListDependencyProvider(environment.dataSource)
    },
    {
      provide: JoinTeammate, useFactory: (ch: TeamCommandHandler, idp: IDProvider) => new JoinTeammate(ch, idp),
      deps: JoinTeammateDependencyProvider(environment.dataSource)
    },
  ]
})
export class TeammateListViewModule { }
