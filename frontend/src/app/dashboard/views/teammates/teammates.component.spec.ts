
import { TeammatesComponent } from './teammates.component';
import { TeammateList } from 'src/core/application/team/query/TeammateList';
import { createStore } from 'src/core/store/Store';
import { fakeAsync, tick } from '@angular/core/testing';
import { Teammate } from 'src/core/application/team/dto/Teammate';
import { teammateList } from 'src/core/application/team/in-memory/data/team.data';
import { InMemoryTeamQueryHandler } from 'src/core/application/team/in-memory/InMemoryTeamQueryHandler';
import { FakeUserContextHolder } from 'src/core/application/team/in-memory/FakeUserContextHolder';
import { ngOnInitPast500Ms } from '../utils/tests.utils';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { TeammateListStoreApi } from 'src/core/store/teammate-list/TeammateListStoreApi';



describe('TeammatesComponent', () => {
  let component: TeammatesComponent;
  let storeApi: TeammateListStoreApi;
  let query: TeammateList;
  beforeEach(() => {
    query = new TeammateList(new InMemoryTeamQueryHandler(), new FakeUserContextHolder())
    storeApi = new TeammateListStoreApi(createStore({...GLOBAL_INITIAL_STATE}))
    component = new TeammatesComponent(storeApi, query);
  });

  it('should load teammateList state', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.teammateList$.subscribe((list: Teammate[])=> {
      expect(list).toEqual(teammateList);
    })
  }));
});
