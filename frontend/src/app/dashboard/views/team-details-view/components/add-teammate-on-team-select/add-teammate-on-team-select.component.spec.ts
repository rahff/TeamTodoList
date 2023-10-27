import { AddTeammateOnTeam } from 'src/core/application/team/command/AddTeammateOnTeam';
import { AddTeammateOnTeamSelectComponent } from './add-teammate-on-team-select.component';
import { InMemoryTeamCommandHandler } from 'src/app/dashboard/services/inMemory/InMemoryTeamCommandHandler';



describe('AddTeammateOnTeamSelectComponent', () => {
  let component: AddTeammateOnTeamSelectComponent;
  let addTeammateOnTeam: AddTeammateOnTeam
  beforeEach(() => {
    addTeammateOnTeam = new AddTeammateOnTeam(new InMemoryTeamCommandHandler())
    component = new AddTeammateOnTeamSelectComponent(addTeammateOnTeam);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
