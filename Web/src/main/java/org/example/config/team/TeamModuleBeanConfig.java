package org.example.config.team;

import org.example.config.security.SecurityTestConfig;
import org.example.config.todo.TodoModuleBeanConfiguration;
import org.example.email.EmailService;
import org.example.transactions.security.CreateTeammateTransaction;
import org.example.transactions.team.AddTeammateOnTeamTransaction;
import org.example.transactions.team.DeleteTeamTransaction;
import org.example.transactions.team.FireTeammateTransaction;
import org.example.transactions.team.RemoveTeammateTransaction;

import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.team.application.commands.*;

import org.team.ports.spi.CodeGenerator;
import org.team.ports.spi.TeamRepository;
import org.team.ports.spi.TeammateRepository;
import org.todo.application.commands.DeleteUserTodoLists;
import org.todo.port.spi.TodoListRepository;


@Configuration
@Profile("prod")
@Import(TodoModuleBeanConfiguration.class)
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
  TeammateRepository teammateRepository;
  @Autowired
  EmailService emailService;


  @Bean
  public CreateTeam createTeamCommand(){
    return new CreateTeam(teamRepository);
  }

  @Bean
  AddTeammateOnTeamTransaction addTeammatesOnTeamCommand(){
    return new AddTeammateOnTeamTransaction(new AddTeammatesOnTeam(teamRepository, teammateRepository));
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
    return new CreateTeammateTransaction(new CreateTeammate(userRepository, codeGenerator, teammateRepository), emailService);
  }

  @Bean
  FireTeammateTransaction fireTeammateCommand() {
    return new FireTeammateTransaction(
            new FireTeammateCommand(
                    teammateRepository,
                    new RemoveTeammateFromTeam(teamRepository)
            ),
            new DeleteUserTodoLists(todoListRepository));
  }
}
