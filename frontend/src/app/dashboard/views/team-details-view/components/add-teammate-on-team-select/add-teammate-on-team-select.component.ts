import { Component, EventEmitter, Input, Output } from '@angular/core';
import { first } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { AddTeammateOnTeam } from 'src/core/application/team/command/AddTeammateOnTeam';
import { AddTeammateOnTeamRequest } from 'src/core/application/team/dto/AddTeammateOnTeamRequest';
import { Teammate } from 'src/core/model/team/Teammate';



@Component({
  selector: 'app-add-teammate-on-team-select',
  templateUrl: './add-teammate-on-team-select.component.html',
  styleUrls: ['./add-teammate-on-team-select.component.css']
})
export class AddTeammateOnTeamSelectComponent {

  @Input() public availableTeammates!: Teammate[];
  @Input() public teamName!: string;
  @Input() public teamId!: string;
  @Output() public teammatesAddedOnTeamEvent = new EventEmitter<Teammate[]>();
  public selectedTeammates: string[] = [];
  
  constructor(private addTeammateOnTeam: AddTeammateOnTeam){}

  public submit(): void {
    if(this.selectedTeammates.length){
      const request: AddTeammateOnTeamRequest = {teamId: this.teamId, teammateIds: this.selectedTeammates}
      this.addTeammateOnTeam.execute(request).pipe(first())
      .subscribe((result: Result<Teammate[]>) => {
        if(result.isOk()) this.teammatesAddedOnTeamEvent.emit(result.getValue());
      })
    }
  }


}
