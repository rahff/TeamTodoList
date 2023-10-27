
import { TeamListViewComponent } from './team-list-view.component';
import { TeamList } from 'src/core/application/team/query/TeamList';
import { createStore } from 'src/core/store/Store';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { fakeAsync } from '@angular/core/testing';
import { Team } from 'src/core/application/team/dto/Team';
import { newTeam, teamList, teamListAfterDeleteTeam2 } from 'src/core/application/team/in-memory/data/team.data';
import { ngOnInitPast500Ms } from '../../root/views/utils/tests.utils';
import { TeamListStoreApi } from 'src/core/store/team-list/TeamListStoreApi';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';
import { InMemoryTeamQueryHandler } from '../../services/inMemory/InMemoryTeamQueryHandler';



describe('TeamComponent', () => {
  let component: TeamListViewComponent;
  let selector: TeamListStoreApi;
  let query: TeamList
  let contextHolder: UserContextHolder;
  
  beforeEach(() => {
    contextHolder = new FakeUserContextHolder()
    selector = new TeamListStoreApi(createStore({...GLOBAL_INITIAL_STATE}));
    query = new TeamList(new InMemoryTeamQueryHandler(), contextHolder)
    component = new TeamListViewComponent(selector, query);
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

  it("a created team event occurs, it add the new team to the application state", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTeamCreatedEvent(newTeam);
    component.teamList$.subscribe((list: Team[])=> {    
      expect(list).toEqual([...teamList, newTeam]);
    })
  }))
});
