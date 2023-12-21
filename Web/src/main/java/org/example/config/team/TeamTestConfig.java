package org.example.config.team;


import org.example.config.security.SecurityTestConfig;
import org.example.email.EmailService;
import org.example.email.FakeEmailService;
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
import org.team.ports.spi.inMemory.FakeCodeGenerator;
import org.team.ports.spi.inMemory.InMemoryTeamRepository;
import org.todo.application.commands.DeleteUserTodoLists;
import org.todo.port.spi.InMemoryTodoListRepository;
import org.todo.port.spi.TodoListRepository;

@Configuration
@Profile("test")
@Import(SecurityTestConfig.class)
public class TeamTestConfig {

    @Autowired
    UserRepository userRepository;
    @Bean
    TeamRepository teamRepository() {
        return new InMemoryTeamRepository();
    }

    @Bean
    CodeGenerator codeGenerator(){
        return new FakeCodeGenerator();
    }
    @Bean
    CreateTeamTransaction createTeamCommand(){
        return new CreateTeamTransaction( new CreateTeam(teamRepository()));
    }

    @Bean
    TodoListRepository _todoListRepository() {
        return new InMemoryTodoListRepository();
    }
    @Bean
    AddTeammateOnTeamTransaction addTeammatesOnTeamCommand(){
        return new AddTeammateOnTeamTransaction(new AddTeammatesOnTeam(teamRepository(), userRepository));
    }

    @Bean
    RemoveTeammateTransaction removeTeammateFromTeamCommand(){
        return new RemoveTeammateTransaction(new RemoveTeammateFromTeam(teamRepository()));
    }
    @Bean
    DeleteTeamTransaction deleteTeamCommand(){
        return new DeleteTeamTransaction(new DeleteTeam(teamRepository()));
    }

    @Bean
    EmailService emailService(){
        return new FakeEmailService();
    }
    @Bean
    CreateTeammateTransaction addTeammate(){
        return new CreateTeammateTransaction(new CreateTeammate(userRepository, codeGenerator()), emailService());
    }

    @Bean
    FireTeammateTransaction fireTeammateCommand() {
        return new FireTeammateTransaction(
                new FireTeammateCommand(
                        userRepository,
                        new RemoveTeammateFromTeam(teamRepository()
                        )
                ),
                new DeleteUserTodoLists(_todoListRepository()));
    }
}
