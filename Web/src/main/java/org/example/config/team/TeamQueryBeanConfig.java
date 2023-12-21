package org.example.config.team;



import org.query.shared.spi.UserDataAccess;
import org.query.team.api.*;
import org.query.team.spi.TeamDataAccess;
import org.query.team.spi.TodoListDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class TeamQueryBeanConfig {

    @Autowired
    private TeamDataAccess teamDataAccess;
    @Autowired
    private TodoListDataAccess todoListDataAccess;
    @Autowired
    private UserDataAccess userDataAccess;

    @Bean
    TeamDetailsViewQuery teamDetailsViewQuery() {
        return new TeamDetailsViewQuery(teamDataAccess, todoListDataAccess, userDataAccess);
    }

    @Bean
    TeamListViewQuery teamListViewQuery(){
        return new TeamListViewQuery(teamDataAccess, todoListDataAccess, userDataAccess);
    }

    @Bean
    TeammateListViewQuery teammateListViewQuery() {
        return new TeammateListViewQuery(userDataAccess, teamDataAccess);
    }

    @Bean
    TeammatesAddedQuery teammateAddedQuery(){
        return new TeammatesAddedQuery(userDataAccess);
    }
    @Bean
    TeamCreatedQuery teamCreatedQuery(){
        return new TeamCreatedQuery(teamDataAccess, todoListDataAccess);
    }
    @Bean
    TeammateCreatedQuery teammateCreatedQuery() {
        return new TeammateCreatedQuery(userDataAccess);
    }
}
