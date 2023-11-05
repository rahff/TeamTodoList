package org.example.config.beans.team;



import org.query.team.api.*;
import org.query.team.spi.TeamDataAccess;
import org.query.team.spi.TodoListDataAccess;
import org.query.team.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
        return new TeamListViewQuery(teamDataAccess, todoListDataAccess);
    }

    @Bean
    TeammateListViewQuery teammateListViewQuery() {
        return new TeammateListViewQuery(userDataAccess, teamDataAccess);
    }

    @Bean
    TeammateAddedQuery teammateAddedQuery(){
        return new TeammateAddedQuery(userDataAccess);
    }
    @Bean
    TeamCreatedQuery teamCreatedQuery(){
        return new TeamCreatedQuery(teamDataAccess, todoListDataAccess);
    }
}
