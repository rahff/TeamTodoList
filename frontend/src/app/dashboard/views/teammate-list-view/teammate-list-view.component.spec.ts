
import { TeammateListViewComponent } from './teammate-list-view.component';
import { TeammateList } from 'src/core/application/team/query/TeammateList';
import { createStore } from 'src/core/store/Store';
import { fakeAsync } from '@angular/core/testing';
import { newTeammate, teammateList, teammateListAfterFiredOne } from 'src/core/application/team/in-memory/data/team.data';
import { ngOnInitPast500Ms } from '../../root/views/utils/tests.utils';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { TeammateListStoreApi } from 'src/core/store/teammate-list/TeammateListStoreApi';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';
import { InMemoryTeamQueryHandler } from '../../services/inMemory/InMemoryTeamQueryHandler';
import { Teammate } from 'src/core/model/team/Teammate';



describe('TeammatesComponent', () => {
  let component: TeammateListViewComponent;
  let storeApi: TeammateListStoreApi;
  let query: TeammateList;
  beforeEach(() => {
    query = new TeammateList(new InMemoryTeamQueryHandler(), new FakeUserContextHolder())
    storeApi = new TeammateListStoreApi(createStore({...GLOBAL_INITIAL_STATE}))
    component = new TeammateListViewComponent(storeApi, query);
  });

  it('should load teammateList state', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.teammateList$.subscribe((list: Teammate[])=> {
      expect(list).toEqual(teammateList);
    })
  }));

  it("should remove the fired teammate from the list", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTeammateFired("mpetfxccxxqq");
    component.teammateList$.subscribe((list: Teammate[])=> {
      expect(list).toEqual(teammateListAfterFiredOne);
    })
  }));

  it("should add the new teammate to the list", fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.onTeammateJoinedEvent(newTeammate);
    component.teammateList$.subscribe((list: Teammate[])=> {
      expect(list).toEqual([...teammateList, newTeammate]);
    })
  }))
});
