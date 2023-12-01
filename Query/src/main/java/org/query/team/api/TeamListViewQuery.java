package org.query.team.api;


import org.query.team.dto.TeamDto;
import org.query.team.model.TeamCard;
import org.query.team.model.TeamListViewModel;
import org.query.team.model.Teammate;
import org.query.team.spi.TeamDataAccess;
import org.query.team.spi.TodoListDataAccess;
import org.query.team.spi.UserDataAccess;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TeamListViewQuery {

    private final TeamDataAccess teamDataAccess;
    private final TodoListDataAccess todoListDataAccess;
    private final UserDataAccess userDataAccess;
    private ExecutorService executorService;

    public TeamListViewQuery(TeamDataAccess teamDataAccess, TodoListDataAccess todoListDataAccess, UserDataAccess userDataAccess) {
        this.teamDataAccess = teamDataAccess;
        this.todoListDataAccess = todoListDataAccess;
        this.userDataAccess = userDataAccess;
    }

    public TeamListViewModel query(String accountId) throws Exception {
        try{
            executorService = Executors.newCachedThreadPool();
            Future<List<String>> availableTeammatesRefs$ = executorService.submit(() -> teamDataAccess.getAvailableTeammatesRef(accountId));
            Future<List<Teammate>> availableTeammates$ = executorService.submit(() -> getAvailableTeammate(availableTeammatesRefs$));
            Future<List<TeamDto>> teamDtoList$ = executorService.submit(() -> teamDataAccess.getAllTeam(accountId));
            var teamCardList = getTeamCardList(teamDtoList$);
            return  new TeamListViewModel(teamCardList, availableTeammates$.get());
        }finally {
            executorService.shutdown();
        }
    }

    private List<TeamCard> getTeamCardList(Future<List<TeamDto>> teamDtoList) throws Exception {
        return teamDtoList.get().parallelStream().map(team -> {
            var todoListCount = todoListDataAccess.getTodoListsCountByTeamId(team.id());
            var teammateCount = team.teammateIds().size();
            return new TeamCard(team.id(), team.name(), teammateCount, todoListCount);
        }).toList();
    }

    private List<Teammate> getAvailableTeammate(Future<List<String>> theirIds$) throws ExecutionException, InterruptedException {
        return theirIds$.get().parallelStream().map(id -> userDataAccess.getTeammateByUserId(id).orElseThrow())
                .map(dto -> new Teammate(dto.id(), dto.name(), dto.email(), null))
                .toList();
    }
}
