import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamDetailsViewRoutingModule } from './team-details-view-routing.module';
import { SharedViewModule } from '../shared-view/shared-view.module';
import { TeamDetailsStoreApi } from 'src/core/store/team-details/TeamDetailsStoreApi';
import { store } from 'src/core/store/Store';
import { TeamById } from 'src/core/application/team/query/TeamById';
import { TeamQueryHandler } from 'src/core/application/team/spi/TeamQueryHandler';
import { InMemoryTeamQueryHandler } from '../../services/inMemory/InMemoryTeamQueryHandler';
import { AddTeammateOnTeamSelectComponent } from './components/add-teammate-on-team-select/add-teammate-on-team-select.component';
import { AddTeammateOnTeam } from 'src/core/application/team/command/AddTeammateOnTeam';
import { TeamCommandHandler } from 'src/core/application/team/spi/TeamCommandHandler';
import { InMemoryTeamCommandHandler } from '../../services/inMemory/InMemoryTeamCommandHandler';




@NgModule({
  declarations: [
    ...TeamDetailsViewRoutingModule.viewComponents,
    AddTeammateOnTeamSelectComponent
  ],
  imports: [
    CommonModule,
    TeamDetailsViewRoutingModule,
    SharedViewModule
  ],
  providers: [
    {
      provide: TeamDetailsStoreApi, useFactory: () => new TeamDetailsStoreApi(store)
    },
    {
      provide: TeamById, useFactory: (q: TeamQueryHandler) => new TeamById(q),
      deps: [InMemoryTeamQueryHandler] 
    },
    {
      provide: AddTeammateOnTeam, useFactory: (c: TeamCommandHandler) => new AddTeammateOnTeam(c),
      deps: [InMemoryTeamCommandHandler] 
    }
  ]
})
export class TeamDetailsViewModule { }
