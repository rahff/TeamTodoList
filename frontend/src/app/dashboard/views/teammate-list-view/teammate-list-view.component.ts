import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { TeammateList } from 'src/core/application/team/query/TeammateList';
import { Teammate } from 'src/core/model/team/Teammate';
import { teammateFiredEvent, teammateJoinedEvent, teammateListReceivedEvent } from 'src/core/store/teammate-list/Events';
import { TeammateListViewModel } from 'src/core/store/teammate-list/TeammateListState';
import { TeammateListStoreApi } from 'src/core/store/teammate-list/TeammateListStoreApi';




@Component({
  selector: 'app-teammate-list-view',
  templateUrl: './teammate-list-view.component.html',
  styleUrls: ['./teammate-list-view.component.css']
})
export class TeammateListViewComponent implements OnInit, OnDestroy {

  public teammateList$!: Observable<Teammate[]>
  private subscription!: Subscription;

  public constructor(private storeApi: TeammateListStoreApi,
              private teammateList: TeammateList) {}

  public ngOnInit(): void {
    this.teammateList$ = this.storeApi.getTeammateList();
    this.subscription = this.teammateList.query().subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(result: Result<TeammateListViewModel>): void {
    if(result.isOk())
      this.storeApi.fireEvent(teammateListReceivedEvent(result.getValue()));
  }

  public onTeammateFired(id: string): void {
    this.storeApi.fireEvent(teammateFiredEvent(id));
  }

  public onTeammateJoinedEvent(teammate: Teammate): void {
    this.storeApi.fireEvent(teammateJoinedEvent(teammate));
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }
}
