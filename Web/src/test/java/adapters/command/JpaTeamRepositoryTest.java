package adapters.command;


import org.example.Main;
import org.example.persistance.entities.team.Team;
import org.example.persistance.mappers.team.TeamMapper;
import org.example.persistance.repositories.team.springData.JpaTeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.team.ports.spi.TeamRepository;
import utils.TeamProvider;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Main.class)
@Profile("prod")
public class JpaTeamRepositoryTest {

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private JpaTeamRepository jpaTeamRepository;

  @Test
  void saveTeam(){
    var teamToSave = TeamProvider.teamDtoWithTwoTeammates();
    teamRepository.saveTeam(teamToSave);
    var expectedTeam = jpaTeamRepository.findById(teamToSave.id()).orElse(null);
    assertNotNull(expectedTeam);
    assertEquals(expectedTeam.getId(), teamToSave.id());
  }
  @Test
  void getTeamById(){
    var savedTeam = saveTeamWithJpa();
    var expectedTeam = teamRepository.getTeamById(savedTeam.getId()).orElse(null);
    assertNotNull(expectedTeam);
    assertEquals(expectedTeam.id(), savedTeam.getId());
  }
  @Test
  void deleteTeam(){
    var teamToSave = TeamProvider.teamDtoWithTwoTeammates();
    var savedTeam = jpaTeamRepository.save(TeamMapper.fromDto(teamToSave));
    teamRepository.deleteTeam(savedTeam.getId());
    var deletedTeam = jpaTeamRepository.findById(savedTeam.getId()).orElse(null);
      assertNull(deletedTeam);
  }

  private Team saveTeamWithJpa() {
    var teamToSave = TeamProvider.teamDtoWithTwoTeammates();
    return jpaTeamRepository.save(TeamMapper.fromDto(teamToSave));
  }
}
