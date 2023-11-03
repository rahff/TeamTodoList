package adapters;


import org.example.Main;
import org.example.persistance.mappers.team.TeamMapper;
import org.example.persistance.repositories.team.command.JpaTeamRepository;
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
    var expectedTeam = teamRepository.getTeamById("cc383b1e-728d-497b-92b5-d85d58cc54c1").orElse(null);
    assertNotNull(expectedTeam);
    assertEquals(expectedTeam.id(), "cc383b1e-728d-497b-92b5-d85d58cc54c1");
  }
  @Test
  void deleteTeam(){
    var teamToSave = TeamProvider.teamDtoWithTwoTeammates();
    var savedTeam = jpaTeamRepository.save(TeamMapper.fromDto(teamToSave));
    teamRepository.deleteTeam(savedTeam.getId());
    var deletedTeam = jpaTeamRepository.findById(savedTeam.getId()).orElse(null);
      assertNull(deletedTeam);
  }

}
