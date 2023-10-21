import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { Team } from 'src/core/application/team/dto/Team';
import { TeamList } from 'src/core/application/team/query/TeamList';
import { teamDeletedEvent, teamListReceivedEvent } from 'src/core/store/team-list/Events';
import { TeamListView } from 'src/core/store/team-list/TeamListState';
import { TeamListStoreApi } from 'src/core/store/team-list/TeamListStoreApi';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit, OnDestroy {

  public teamList$!: Observable<Team[]>;
  private subscription!: Subscription;

  constructor(private storeApi: TeamListStoreApi, private teamList: TeamList) { }

  ngOnInit(): void {
    this.teamList$ = this.storeApi.getTeamList();
    this.subscription = this.teamList.query().subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(listResult: Result<TeamListView>): void {
    if(listResult.isOk()) this.storeApi.fireEvent(teamListReceivedEvent(listResult.getValue()))
  }

  public onTeamDeletedEvent(id: string): void {
    this.storeApi.fireEvent(teamDeletedEvent(id));
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }
}
