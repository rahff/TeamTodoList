package org.query.team.api;

import org.query.team.dto.TeamDto;
import org.query.team.model.TeamCard;
import org.query.team.spi.TeamDataAccess;
import org.query.team.spi.TodoListDataAccess;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TeamCreatedQuery {

    private final TeamDataAccess teamDataAccess;
    private final TodoListDataAccess todoListDataAccess;
    private ExecutorService executorService;

    public TeamCreatedQuery(TeamDataAccess teamDataAccess, TodoListDataAccess todoListDataAccess) {
        this.teamDataAccess = teamDataAccess;
        this.todoListDataAccess = todoListDataAccess;
    }

    public TeamCard getTeamCard(String id) throws ExecutionException, InterruptedException {
        try{
            executorService = Executors.newCachedThreadPool();
            Future<Integer> todoListCount$ = executorService.submit(()-> todoListDataAccess.getTodoListsCountByTeamId(id));
            Future<TeamDto> team$ = executorService.submit(() -> teamDataAccess.getTeamById(id).orElseThrow());
            var team = team$.get();
            return new TeamCard(team.id(), team.name(), team.teammateIds().size(), todoListCount$.get());
        }finally {
            executorService.shutdown();
        }
    }
}
