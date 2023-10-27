import { Team } from 'src/core/application/team/dto/Team';
import { TeamDetailsViewComponent } from './team-details-view.component';
import { TeamById } from 'src/core/application/team/query/TeamById';
import { newTeammate, team2, team2AfterAddTeammate, team2AfterRemoveTeammate2 } from 'src/core/application/team/in-memory/data/team.data';
import { createStore } from 'src/core/store/Store';
import { todoListsForTeamRefAfterDeletingTheSecond } from 'src/core/application/todo/in-memory/data/data.todo';
import { fakeAsync, tick } from '@angular/core/testing';
import { FakeActivatedRoute, ngOnInitPast500Ms } from '../../root/views/utils/tests.utils';
import { TeamDetailsStoreApi } from 'src/core/store/team-details/TeamDetailsStoreApi';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { InMemoryTeamQueryHandler } from '../../services/inMemory/InMemoryTeamQueryHandler';





describe('TeamDetailsComponent', () => {
  let component: TeamDetailsViewComponent;
  let storeApi: TeamDetailsStoreApi;
  let query: TeamById;
  let activatedRoute: FakeActivatedRoute
  beforeEach(() => {
    activatedRoute = new FakeActivatedRoute(team2.id, "id")
    query = new TeamById(new InMemoryTeamQueryHandler());
    storeApi = new TeamDetailsStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
    component = new TeamDetailsViewComponent(storeApi, query, activatedRoute);
  });
  
  it('should load the team details', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.teamDetails$.subscribe((details: Team | null) => {
      expect(details).toEqual(team2);
    })
  }));
  
  it("should update the state on todolistDeleted event", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTodoListDeleted("pejfjefzsfdefjekke");
    component.teamDetails$.subscribe((details: Team | null) => {
      expect(details?.todoLists).toEqual(todoListsForTeamRefAfterDeletingTheSecond);
    })
  }));

  it("should update the state on teammateFired event", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.removeTeammateFromTeam("mplokijuhygt");
    component.teamDetails$.subscribe((details: Team | null) => {
      expect(details?.teammates).toEqual(team2AfterRemoveTeammate2.teammates);
    })
  }))

  it("should update the state on teammateFired event", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onAddTeammatesOnTeamSubmited([{...newTeammate, teamId: "fghjklopiuyt"}]);
    component.teamDetails$.subscribe((details: Team | null) => {
      expect(details?.teammates).toEqual(team2AfterAddTeammate.teammates);
    })
  }))
});
