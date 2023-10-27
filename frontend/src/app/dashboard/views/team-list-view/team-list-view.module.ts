import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamListViewRoutingModule } from './team-list-view-routing.module';
import { TeamListDropdownComponent } from './components/team-list-dropdown/team-list-dropdown.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { AddTeamFormComponent } from './components/add-team-form/add-team-form.component';
import { TeamListStoreApi } from 'src/core/store/team-list/TeamListStoreApi';
import { store } from 'src/core/store/Store';
import { TeamList } from 'src/core/application/team/query/TeamList';
import { TeamQueryHandler } from 'src/core/application/team/spi/TeamQueryHandler';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryTeamQueryHandler } from '../../services/inMemory/InMemoryTeamQueryHandler';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';
import { CreateTeam } from 'src/core/application/team/command/CreateTeam';
import { DeleteTeam } from 'src/core/application/team/command/DeleteTeam';
import { TeamCommandHandler } from 'src/core/application/team/spi/TeamCommandHandler';
import { IDProvider } from 'src/core/application/shared/interfaces/IDProvider';
import { InMemoryTeamCommandHandler } from '../../services/inMemory/InMemoryTeamCommandHandler';
import { UUIDService } from '../../services/uuid.service';




@NgModule({
  declarations: [
    ...TeamListViewRoutingModule.viewComponents,
    TeamListDropdownComponent,
    TeamListComponent,
    AddTeamFormComponent
  ],
  imports: [
    CommonModule,
    TeamListViewRoutingModule
  ],
  providers: [
    {
      provide: TeamListStoreApi, useFactory: () => new TeamListStoreApi(store)
    },
    {
      provide: TeamList, useFactory: (q: TeamQueryHandler, u: UserContextHolder) => new TeamList(q, u),
      deps: [InMemoryTeamQueryHandler, FakeUserContextHolder]
    },
    {
      provide: CreateTeam, useFactory: (c: TeamCommandHandler, i: IDProvider) => new CreateTeam(c, i),
      deps: [InMemoryTeamCommandHandler, UUIDService]
    },
    {
      provide: DeleteTeam, useFactory: (c: TeamCommandHandler) => new DeleteTeam(c),
      deps: [InMemoryTeamCommandHandler]
    }
  ]
})
export class TeamListViewModule { }
