
import { TeamListViewComponent } from './team-list-view.component';
import { TeamList } from 'src/core/application/team/query/TeamList';
import { createStore } from 'src/core/store/Store';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { fakeAsync } from '@angular/core/testing';
import {
  newTeam,
  newTeamCard,
  teamCardList,
  teamList,
  teamCardListAfterDeleteTeam2,
  availableTeammates
} from 'src/core/application/team/in-memory/data/team.data';
import { ngOnInitPast500Ms } from '../../root/views/utils/tests.utils';
import { TeamListStoreApi } from 'src/core/store/team-list/TeamListStoreApi';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';
import { InMemoryTeamQueryHandler } from '../../services/inMemory/InMemoryTeamQueryHandler';
import { TeamCard } from 'src/core/model/team/TeamCard';
import {TeamListViewModel} from "../../../../core/store/team-list/TeamListState";



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
    component.teamListView$.subscribe((list: TeamListViewModel)=> {
      expect(list).toEqual({viewModel:{list: teamCardList, availableTeammates} });
    })
  }));

  it("a deleted team event occurs, it delete the team from the application state", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTeamDeletedEvent("xcvbtghrtyui");
    component.teamListView$.subscribe((list: TeamListViewModel)=> {
      expect(list).toEqual({viewModel:{list: teamCardListAfterDeleteTeam2, availableTeammates} });
    })
  }))

  it("a created team event occurs, it add the new team to the application state", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTeamCreatedEvent(newTeamCard);
    component.teamListView$.subscribe((list: TeamListViewModel)=> {
      expect(list).toEqual({viewModel:{list: [...teamCardList, newTeamCard], availableTeammates} });
    })
  }))
});
