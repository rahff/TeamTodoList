package adapters.query;


import org.example.Main;
import org.example.persistance.mappers.team.TeamMapper;
import org.example.persistance.repositories.security.springData.AppUserRepository;
import org.example.persistance.repositories.team.springData.JpaTeamRepository;
import org.example.persistance.repositories.team.query.JpaTeamQueryTeamDataAccess;
import org.junit.jupiter.api.Test;
import org.query.team.dto.TeamDto;
import org.security.application.CreateManagerAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.team.application.commands.AddTeammatesOnTeam;
import org.team.application.commands.CreateTeam;
import org.team.application.commands.CreateTeammate;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.dto.CreateTeamRequest;
import org.team.ports.dto.CreateTeammateRequest;
import utils.StringProvider;
import utils.TeamProvider;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
@Profile("prod")
public class JpaTeamQueryTeamDataAccessTest {

  @Autowired
  private JpaTeamQueryTeamDataAccess sutTeamDataAccess;
  @Autowired
  private CreateTeammate createTeammate;

  @Autowired
  private JpaTeamRepository springDataTeamRepository;

  @Autowired
  private AppUserRepository springDataUserRepository;

  @Autowired
  CreateManagerAccount createManagerAccount;

  @Autowired
  CreateTeam createTeam;

  @Autowired
  AddTeammatesOnTeam addTeammatesOnTeam;

  @Test
  void JpaTeamQueryTeamDataAccess_getTeamById() {
    var createdTeam = springDataTeamRepository.save(TeamMapper.fromDto(TeamProvider.emptyTeamDto()));
    var foundedTeamInDb = sutTeamDataAccess.getTeamById(createdTeam.getId()).orElseThrow();
    assertEquals(createdTeam.getId(), foundedTeamInDb.id());
  }

  @Test
  void JpaTeamQueryTeamDataAccess_getAvailableTeammatesRef() {
    var fakeAccountId = StringProvider.unique();
    var teammateId = StringProvider.unique();
    createTeammate.execute(new CreateTeammateRequest(teammateId, StringProvider.unique(), "Jean", fakeAccountId));
    var availableTeammateForFakeAccount = sutTeamDataAccess.getAvailableTeammatesRef(fakeAccountId);
    assertArrayEquals(List.of(teammateId).toArray(), availableTeammateForFakeAccount.toArray());
  }

  @Test
  void JpaTeamQueryTeamDataAccess_getAllTeam() {
    var accountId = StringProvider.unique();
    var teamId = StringProvider.unique();
    createManagerAccount.execute(accountId);
    createTeam.execute(new CreateTeamRequest(teamId, "Team fake", List.of(), accountId));
    var result = sutTeamDataAccess.getAllTeam(accountId);
    assertArrayEquals(List.of(new TeamDto(teamId, "Team fake", List.of())).toArray(), result.toArray());
  }
  @Test
  void JpaTeamQueryTeamDataAccess_getTeamIdOfTeammate(){
    var accountId = StringProvider.unique();
    var teamId = StringProvider.unique();
    var teammateId = StringProvider.unique();
    createTeam.execute(new CreateTeamRequest(teamId, "Team fake", List.of(), accountId));

    createTeammate.execute(new CreateTeammateRequest(teammateId, StringProvider.unique(), "Jean", accountId));
    addTeammatesOnTeam.execute(new AddTeammatesOnTeamRequest(teamId, List.of(teammateId)));
    var teamIdOfTeammateResult = sutTeamDataAccess.getTeamIdOfTeammate(teammateId).orElseThrow();
    assertEquals(teamId, teamIdOfTeammateResult);
  }
}
