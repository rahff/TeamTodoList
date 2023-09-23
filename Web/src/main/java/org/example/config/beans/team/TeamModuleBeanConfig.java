package org.example.config.beans.team;

import org.shared.api.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.team.application.commands.AddTeammatesOnTeam;
import org.team.application.commands.CreateTeam;
import org.team.application.commands.DeleteTeam;
import org.team.application.commands.RemoveTeammateFromTeam;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.dto.CreateTeamRequest;
import org.team.ports.dto.DeleteTeamRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.spi.TeamRepository;

@Configuration
public class TeamModuleBeanConfig {

  @Autowired
  private TeamRepository teamRepository;

  @Bean
  public Command<CreateTeamRequest> createTeamRequestCommand(){
    return new CreateTeam(teamRepository);
  }

  @Bean
  Command<AddTeammatesOnTeamRequest> addTeammatesOnTeamRequestCommand(){
    return new AddTeammatesOnTeam(teamRepository);
  }

  @Bean
  Command<RemoveTeammateFromTeamRequest> removeTeammateFromTeamRequestCommand(){
    return new RemoveTeammateFromTeam(teamRepository);
  }
  @Bean
  Command<DeleteTeamRequest> deleteTeamRequestCommand(){
    return new DeleteTeam(teamRepository);
  }
}
