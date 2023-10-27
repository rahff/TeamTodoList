import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ConfirmActionService } from 'src/app/dashboard/services/confirm-action.service';
import { Result } from 'src/core/application/shared/dto/Result';
import { AddTeammateOnTeam } from 'src/core/application/team/command/AddTeammateOnTeam';
import { FireTeammate } from 'src/core/application/team/command/FireTeammate';
import { RemoveTeammateFromTeam } from 'src/core/application/team/command/RemoveTeammateFromTeam';
import { Teammate } from 'src/core/application/team/dto/Teammate';



@Component({
  selector: 'app-teammate-list',
  templateUrl: './teammate-list.component.html',
  styleUrls: ['./teammate-list.component.css']
})
export class TeammateListComponent implements OnInit {

  @Input() public teamId!: string;
  @Input() public teammates!: Teammate[];
  @Output() public teammateFiredEvent = new EventEmitter<string>();
  @Output() public teammateRemovedFromTeamEvent = new EventEmitter<string>();
  
  constructor(private fireTeammate: FireTeammate,
              private removeTeammateFromTeam: RemoveTeammateFromTeam,
              private addTeammateOnTeam: AddTeammateOnTeam,
              private confirmService: ConfirmActionService) { }

  ngOnInit(): void {
  }

  public onfireTeammateButtonClicked(id: string): void {
    this.confirmService.askConfirmationFor(this.fireTeammate, id)
    .subscribe((result: Result<string>) => {
      if(result.isOk()) this.teammateFiredEvent.emit(result.getValue())
    })
  }

  public onRemoveTeammateFromTeamButtonClicked(id: string): void {
    this.confirmService.askConfirmationFor(this.removeTeammateFromTeam, {teamId: this.teamId, teammateId: id})
    .subscribe((result: Result<string>) => {
      if(result.isOk()) this.teammateRemovedFromTeamEvent.emit(result.getValue())
    })
  }

}
