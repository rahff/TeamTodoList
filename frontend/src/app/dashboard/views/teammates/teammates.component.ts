import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { Teammate } from 'src/core/application/team/dto/Teammate';
import { TeammateList } from 'src/core/application/team/query/TeammateList';
import { teammateListReceivedEvent } from 'src/core/store/teammate-list/Events';
import { TeammateListView } from 'src/core/store/teammate-list/TeammateListState';
import { TeammateListStoreApi } from 'src/core/store/teammate-list/TeammateListStoreApi';




@Component({
  selector: 'app-teammates',
  templateUrl: './teammates.component.html',
  styleUrls: ['./teammates.component.css']
})
export class TeammatesComponent implements OnInit, OnDestroy {

  public teammateList$!: Observable<Teammate[]>
  private subscription!: Subscription;

  constructor(private storeApi: TeammateListStoreApi, private teammateList: TeammateList) { }

  public ngOnInit(): void {
    this.teammateList$ = this.storeApi.getTeammateList();
    this.subscription = this.teammateList.query().subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(result: Result<TeammateListView>): void {
    if(result.isOk())
    this.storeApi.fireEvent(teammateListReceivedEvent(result.getValue()));
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }

}
