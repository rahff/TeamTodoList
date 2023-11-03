package org.queryTeam.api;

import org.queryTeam.model.TeamCard;
import org.queryTeam.model.TeamListViewModel;
import org.queryTeam.spi.TeamDataAccess;
import org.queryTeam.spi.TodoListDataAccess;

public class TeamListViewQuery {

    private final TeamDataAccess teamDataAccess;
    private final TodoListDataAccess todoListDataAccess;

    public TeamListViewQuery(TeamDataAccess teamDataAccess, TodoListDataAccess todoListDataAccess) {
        this.teamDataAccess = teamDataAccess;
        this.todoListDataAccess = todoListDataAccess;
    }

    public TeamListViewModel query(String accountId) {
        var teamDtoList = teamDataAccess.getAllTeam(accountId);
        var teamCardList = teamDtoList.stream().parallel().map(team -> {
            var todoListCount = todoListDataAccess.getTodoListsCountByTeamId(team.id());
            var teammateCount = team.teammateIds().size();
            return new TeamCard(team.id(), team.name(), teammateCount, todoListCount);
        }).toList();
        return  new TeamListViewModel(teamCardList);
    }
}
