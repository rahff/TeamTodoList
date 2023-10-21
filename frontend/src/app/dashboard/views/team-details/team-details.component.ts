import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { Team } from 'src/core/application/team/dto/Team';
import { TeamById } from 'src/core/application/team/query/TeamById';
import { TodoList } from 'src/core/application/todo/dto/TodoList';
import { teamDetailsReceivedEvent, teamTodoListDeletedEvent } from 'src/core/store/team-details/Events';
import { TeamDetailsView } from 'src/core/store/team-details/TeamDetailsState';
import { TeamDetailsStoreApi } from 'src/core/store/team-details/TeamDetailsStoreApi';




@Component({
  selector: 'app-team-details',
  templateUrl: './team-details.component.html',
  styleUrls: ['./team-details.component.css']
})
export class TeamDetailsComponent implements OnInit {

  public teamDetails$!: Observable<Team | null>;

  constructor(private storeApi: TeamDetailsStoreApi, 
              private teamDetails: TeamById,
              private activatedRoute: ActivatedRouteSnapshot) { }

  public ngOnInit(): void {
  
    this.teamDetails$ = this.storeApi.getTeamDetails();
    this.teamDetails.query(this.getTeamId()).subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(result: Result<TeamDetailsView>): void {
    if(result.isOk()){
      this.storeApi.fireEvent(teamDetailsReceivedEvent(result.getValue()));
    } 
  }

  private getTeamId(): string {
    return this.activatedRoute.paramMap.get("id") || "";
  }

  public onTodoListDeleted(id: string): void {
    this.storeApi.fireEvent(teamTodoListDeletedEvent(id));
  }

}
