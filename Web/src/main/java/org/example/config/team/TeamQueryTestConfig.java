package org.example.config.team;



import org.example.config.account.AccountQueryTestConfig;
import org.query.shared.spi.UserDataAccess;
import org.query.team.api.*;
import org.query.team.spi.TeamDataAccess;
import org.query.team.spi.TodoListDataAccess;

import org.query.team.spi.fakes.InMemoryTeamDataAccess;
import org.query.team.spi.fakes.InMemoryTodoListDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@Import({AccountQueryTestConfig.class})
public class TeamQueryTestConfig {

    @Autowired
    UserDataAccess userDataAccess;
    @Bean
    TodoListDataAccess todoListDataAccessFromTeamContext(){
        return new InMemoryTodoListDataAccess();
    }
    @Bean
    TeamDataAccess teamDataAccess(){
        return new InMemoryTeamDataAccess();
    }

    @Bean
    TeamDetailsViewQuery teamDetailsViewQuery() {
        return new TeamDetailsViewQuery(teamDataAccess(), todoListDataAccessFromTeamContext(), userDataAccess);
    }

    @Bean
    TeamListViewQuery teamListViewQuery(){
        return new TeamListViewQuery(teamDataAccess(), todoListDataAccessFromTeamContext(), userDataAccess);
    }

    @Bean
    TeammateListViewQuery teammateListViewQuery() {
        return new TeammateListViewQuery(userDataAccess, teamDataAccess());
    }

    @Bean
    TeammatesAddedQuery teammateAddedQuery(){
        return new TeammatesAddedQuery(userDataAccess);
    }
    @Bean
    TeamCreatedQuery teamCreatedQuery(){
        return new TeamCreatedQuery(teamDataAccess(), todoListDataAccessFromTeamContext());
    }
    @Bean
    TeammateCreatedQuery teammateCreatedQuery() {
        return new TeammateCreatedQuery(userDataAccess);
    }
}
