package org.query.team.api;


import org.query.team.model.TeamCard;
import org.query.team.model.TeamListViewModel;
import org.query.team.spi.TeamDataAccess;
import org.query.team.spi.TodoListDataAccess;

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
