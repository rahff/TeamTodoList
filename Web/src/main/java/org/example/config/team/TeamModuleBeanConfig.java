package org.example.config.team;

import org.shared.api.Command;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.team.application.commands.*;
import org.team.ports.api.AddTeammate;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.dto.CreateTeamRequest;
import org.team.ports.dto.DeleteTeamRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.spi.CodeGenerator;
import org.team.ports.spi.TeamRepository;
import org.team.ports.spi.TeammateRepository;

@Configuration
public class TeamModuleBeanConfig {

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CodeGenerator codeGenerator;
  @Autowired
  TeammateRepository teammateRepository;


  @Bean
  public Command<CreateTeamRequest> createTeamRequestCommand(){
    return new CreateTeam(teamRepository);
  }

  @Bean
  Command<AddTeammatesOnTeamRequest> addTeammatesOnTeamRequestCommand(){
    return new AddTeammatesOnTeam(teamRepository, teammateRepository);
  }

  @Bean
  Command<RemoveTeammateFromTeamRequest> removeTeammateFromTeamRequestCommand(){
    return new RemoveTeammateFromTeam(teamRepository);
  }
  @Bean
  Command<DeleteTeamRequest> deleteTeamRequestCommand(){
    return new DeleteTeam(teamRepository);
  }

  @Bean
  AddTeammate addTeammate(){
    return new CreateTeammate(userRepository, codeGenerator, teammateRepository);
  }
}
