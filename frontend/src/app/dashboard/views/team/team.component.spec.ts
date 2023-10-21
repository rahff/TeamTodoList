
import { TeamComponent } from './team.component';
import { TeamList } from 'src/core/application/team/query/TeamList';
import { createStore } from 'src/core/store/Store';
import { InMemoryTeamQueryHandler } from 'src/core/application/team/in-memory/InMemoryTeamQueryHandler';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { fakeAsync, tick } from '@angular/core/testing';
import { Team } from 'src/core/application/team/dto/Team';
import { teamList, teamListAfterDeleteTeam2 } from 'src/core/application/team/in-memory/data/team.data';
import { FakeUserContextHolder } from 'src/core/application/team/in-memory/FakeUserContextHolder';
import { ngOnInitPast500Ms } from '../utils/tests.utils';
import { TeamListStoreApi } from 'src/core/store/team-list/TeamListStoreApi';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';



describe('TeamComponent', () => {
  let component: TeamComponent;
  let selector: TeamListStoreApi;
  let query: TeamList
  let contextHolder: UserContextHolder;
  
  beforeEach(() => {
    contextHolder = new FakeUserContextHolder()
    selector = new TeamListStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
    query = new TeamList(new InMemoryTeamQueryHandler(), contextHolder)
    component = new TeamComponent(selector, query);
  });
  
  it('should load teamList state', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.teamList$.subscribe((list: Team[])=> {    
      expect(list).toEqual(teamList);
    })
  }));

  it("a deleted team event occurs, it delete the team from the application state", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTeamDeletedEvent("xcvbtghrtyui");
    component.teamList$.subscribe((list: Team[])=> {    
      expect(list).toEqual(teamListAfterDeleteTeam2);
    })
  }))
});
