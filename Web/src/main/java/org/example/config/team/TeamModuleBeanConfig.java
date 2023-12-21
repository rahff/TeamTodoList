package org.example.config.team;

import org.example.config.security.SecurityConfig;
import org.example.config.todo.TodoModuleBeanConfiguration;
import org.example.email.EmailService;
import org.example.transactions.security.CreateTeammateTransaction;
import org.example.transactions.team.*;

import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.team.application.commands.*;

import org.team.ports.spi.CodeGenerator;
import org.team.ports.spi.TeamRepository;
import org.todo.application.commands.DeleteUserTodoLists;
import org.todo.port.spi.TodoListRepository;


@Configuration
@Profile("prod")
@Import({TodoModuleBeanConfiguration.class, SecurityConfig.class})
public class TeamModuleBeanConfig {

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  UserRepository userRepository;
  @Autowired
  TodoListRepository todoListRepository;

  @Autowired
  CodeGenerator codeGenerator;
  @Autowired
  EmailService emailService;

  @Bean
  CreateTeammate createTeammate(){
    return new CreateTeammate(userRepository, codeGenerator);
  }
  @Bean
  CreateTeam createTeam(){
    return new CreateTeam(teamRepository);
  }
  @Bean
  public CreateTeamTransaction createTeamCommand(){
    return new CreateTeamTransaction(createTeam());
  }
  @Bean
  AddTeammatesOnTeam addTeammatesOnTeam(){
    return new AddTeammatesOnTeam(teamRepository, userRepository);
  }

  @Bean
  AddTeammateOnTeamTransaction addTeammatesOnTeamCommand(){
    return new AddTeammateOnTeamTransaction(addTeammatesOnTeam());
  }

  @Bean
  RemoveTeammateTransaction removeTeammateFromTeamCommand(){
    return new RemoveTeammateTransaction(new RemoveTeammateFromTeam(teamRepository));
  }
  @Bean
  DeleteTeamTransaction deleteTeamCommand(){
    return new DeleteTeamTransaction(new DeleteTeam(teamRepository));
  }

  @Bean
  CreateTeammateTransaction addTeammate(){
    return new CreateTeammateTransaction(createTeammate(), emailService);
  }

  @Bean
  FireTeammateCommand fireTeammateCommand(){
    return new FireTeammateCommand(userRepository,
            new RemoveTeammateFromTeam(teamRepository));
  }
  @Bean
  FireTeammateTransaction fireTeammateTransaction() {
    return new FireTeammateTransaction(
            fireTeammateCommand(),
            new DeleteUserTodoLists(todoListRepository));
  }
}
