package org.example.config.beans.team;


import org.queryTeam.api.TeamDetailsViewQuery;
import org.queryTeam.api.TeamListViewQuery;
import org.queryTeam.spi.TeamDataAccess;
import org.queryTeam.spi.TodoListDataAccess;
import org.queryTeam.spi.UserDataAccess;
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
}
