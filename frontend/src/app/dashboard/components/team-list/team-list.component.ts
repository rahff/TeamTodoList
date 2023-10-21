import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { ConfirmActionService } from 'src/app/services/confirm-action.service';
import { Result } from 'src/core/application/shared/dto/Result';
import { DeleteTeam } from 'src/core/application/team/command/DeleteTeam';
import { Team } from 'src/core/application/team/dto/Team';

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent implements OnDestroy {

  @Input() public teamList!: Team[];
  @Output() private teamDeletedEvent = new EventEmitter<string>();
  private subcription = new Subscription();

  constructor(private deleteTeam: DeleteTeam, 
              private confirmService: ConfirmActionService) {}

  public onDeleteActionClicked(id: string): void {
    this.subcription.add(this.confirmService.askConfirmationFor(this.deleteTeam, id)
    .subscribe({ next: this.onResult.bind(this) }));
  }

  private onResult(result: Result<string>): void {
    if(result.isOk()) this.teamDeletedEvent.emit(result.getValue());
  }

  public ngOnDestroy(): void {
      this.subcription.unsubscribe();
  }

}
