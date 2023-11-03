import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription, first, pipe } from 'rxjs';
import { ConfirmActionService } from 'src/app/dashboard/services/confirm-action.service';
import { Result } from 'src/core/application/shared/dto/Result';
import { CreateTeam } from 'src/core/application/team/command/CreateTeam';
import { DeleteTeam } from 'src/core/application/team/command/DeleteTeam';
import { CreateTeamFormData } from 'src/core/application/team/dto/CreateTeamFormData';
import { TeamCard } from 'src/core/model/team/TeamCard';


@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent implements OnDestroy {

  @Input() public teamList!: TeamCard[];
  @Output() private teamDeletedEvent = new EventEmitter<string>();
  @Output() private teamCreatedEvent = new EventEmitter<TeamCard>();
  private subcription = new Subscription();

  constructor(private deleteTeam: DeleteTeam, 
              private createTeam: CreateTeam,
              private confirmService: ConfirmActionService) {}

  public onDeleteActionClicked(id: string): void {
    this.subcription.add(this.confirmService.askConfirmationFor(this.deleteTeam, id)
    .pipe(first()).subscribe({ next: this.onResult.bind(this) }));
  }

  private onResult(result: Result<string>): void {
    console.log(result);
    
    if(result.isOk()) this.teamDeletedEvent.emit(result.getValue());
  }

  public onTeamFormSubmited(data: {name: string, teammates: string[]}): void {
    const formData: CreateTeamFormData = {
      name: data.name,
      teammates: data.teammates
    }
    this.createTeam.execute(formData).pipe(first()).subscribe({
      next: this.onCreateTeamResult.bind(this)
    })
  }

  private onCreateTeamResult(result: Result<TeamCard>): void {
    if(result.isOk()) this.teamCreatedEvent.emit(result.getValue());
  }

  public ngOnDestroy(): void {
      this.subcription.unsubscribe();
  }

}
