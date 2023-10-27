import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { Team } from 'src/core/application/team/dto/Team';
import { Teammate } from 'src/core/application/team/dto/Teammate';
import { TeamById } from 'src/core/application/team/query/TeamById';

import { teamDetailsReceivedEvent, teamTodoListDeletedEvent, teammateRemovedFromTeamEvent, teammatesOnTeamAddedEvent } from 'src/core/store/team-details/Events';
import { TeamDetailsViewModel } from 'src/core/store/team-details/TeamDetailsState';
import { TeamDetailsStoreApi } from 'src/core/store/team-details/TeamDetailsStoreApi';





@Component({
  selector: 'app-team-details-view',
  templateUrl: './team-details-view.component.html',
  styleUrls: ['./team-details-view.component.css']
})
export class TeamDetailsViewComponent implements OnInit, OnDestroy {

  public teamDetails$!: Observable<Team | null>;
  public availableTeammate$!: Observable<Teammate[]>;
  private subscription!: Subscription

  constructor(private storeApi: TeamDetailsStoreApi, 
              private teamDetails: TeamById,
              private activatedRoute: ActivatedRoute) { }

  public ngOnInit(): void {
    this.availableTeammate$ = this.storeApi.getAvailableTeammates()
    this.teamDetails$ = this.storeApi.getTeamDetails();
    this.subscription = this.teamDetails.query(this.getTeamId()).subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(result: Result<TeamDetailsViewModel>): void {
    if(result.isOk()){
      this.storeApi.fireEvent(teamDetailsReceivedEvent(result.getValue()));
    } 
  }

  private getTeamId(): string {
    return this.activatedRoute.snapshot.paramMap.get("id") || "";
  }

  public onTodoListDeleted(id: string): void {
    this.storeApi.fireEvent(teamTodoListDeletedEvent(id));
  }

  public removeTeammateFromTeam(id: string): void {
    this.storeApi.fireEvent(teammateRemovedFromTeamEvent(id));
  }

  public onAddTeammatesOnTeamSubmited(teammates: Teammate[]): void {
    this.storeApi.fireEvent(teammatesOnTeamAddedEvent(teammates));
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }

}
