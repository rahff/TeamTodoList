import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TeamQueryHandler } from 'src/core/application/team/spi/TeamQueryHandler';
import { TeamDetailsViewModel } from 'src/core/store/team-details/TeamDetailsState';
import { TeamListViewModel } from 'src/core/store/team-list/TeamListState';
import { TeammateListViewModel } from 'src/core/store/teammate-list/TeammateListState';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class TeamQueryService implements TeamQueryHandler {

  private baseServerUrl: string = environment.serverUrl;

  public constructor(private http: HttpClient) { }

  public getTeamList(accountId: string): Observable<TeamListViewModel> {
    return this.http.get<TeamListViewModel>(this.baseServerUrl+`/teams/${accountId}`);
  }

  public getTeamById(teamId: string): Observable<TeamDetailsViewModel> {
    return this.http.get<TeamDetailsViewModel>(this.baseServerUrl+`/team-details/${teamId}`);
  }

  public getTeammateList(accountId: string): Observable<TeammateListViewModel> {
    return this.http.get<TeammateListViewModel>(this.baseServerUrl+`/teammates/${accountId}`);
  }
}
