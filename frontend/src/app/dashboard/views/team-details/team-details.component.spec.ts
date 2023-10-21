import { Team } from 'src/core/application/team/dto/Team';
import { TeamDetailsComponent } from './team-details.component';
import { TeamById } from 'src/core/application/team/query/TeamById';
import { team2 } from 'src/core/application/team/in-memory/data/team.data';
import { createStore } from 'src/core/store/Store';
import { todoListsForTeamRef, todoListsForTeamRefAfterDeletingTheSecond } from 'src/core/application/todo/in-memory/data/data.todo';
import { InMemoryTeamQueryHandler } from 'src/core/application/team/in-memory/InMemoryTeamQueryHandler';
import { fakeAsync, tick } from '@angular/core/testing';
import { FakeActivatedRouteSnaphot, ngOnInitPast500Ms } from '../utils/tests.utils';
import { TodoList } from 'src/core/application/todo/dto/TodoList';
import { TeamDetailsStoreApi } from 'src/core/store/team-details/TeamDetailsStoreApi';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';





describe('TeamDetailsComponent', () => {
  let component: TeamDetailsComponent;
  let storeApi: TeamDetailsStoreApi;
  let query: TeamById;
  let activatedRoute: FakeActivatedRouteSnaphot
  beforeEach(() => {
    activatedRoute = new FakeActivatedRouteSnaphot(team2.id, "id")
    query = new TeamById(new InMemoryTeamQueryHandler());
    storeApi = new TeamDetailsStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
    component = new TeamDetailsComponent(storeApi, query, activatedRoute);
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
  }))
});
