import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { AddTeammateOnTeamRequest } from 'src/core/application/team/dto/AddTeammateOnTeamRequest';
import { CreateTeamRequest } from 'src/core/application/team/dto/CreateTeamFormData';
import { JoinTeammateRequest } from 'src/core/application/team/dto/CreateTeammateFormData';
import { RemoveTeammateFromTeamRequest } from 'src/core/application/team/dto/RemoveTeammateFromTeamRequest';
import { TeamCommandHandler } from 'src/core/application/team/spi/TeamCommandHandler';
import { TeamCard } from 'src/core/model/team/TeamCard';
import { Teammate } from 'src/core/model/team/Teammate';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeamCommandService implements TeamCommandHandler {

  private baseUrlServer: string = environment.serverUrl;

  public constructor(private http: HttpClient) { }

  public createTeam(request: CreateTeamRequest): Observable<TeamCard> {
    return this.http.post<TeamCard>(this.baseUrlServer+"/create-team", request);
  }

  public joinTeammate(request: JoinTeammateRequest): Observable<Teammate> {
    return this.http.post<Teammate>(this.baseUrlServer+"/create-teammate", request);
  }

  public deleteTeam(teamId: string): Observable<string> {
    return this.http.delete<{id: string}>(this.baseUrlServer+`/delete-team/${teamId}`)
    .pipe(map((json) => json.id));
  }

  public addTeammatesOnTeam(request: AddTeammateOnTeamRequest): Observable<Teammate[]> {
    return this.http.post<Teammate[]>(this.baseUrlServer+"/add-teammates-on-team", request);
  }

  public removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<string> {
    return this.http.put<{id: string}>(this.baseUrlServer+"/remove-teammate-from-team", request)
    .pipe(map((json) => json.id));
  }

  public fireTeammate(teammate: Teammate): Observable<string> {
    const request = {userId: teammate.id, teamId: teammate.teamId}
    return this.http.put<{id: string}>(this.baseUrlServer+`/fire-teammate`, request)
    .pipe(map((json) => json.id));
  }
}
