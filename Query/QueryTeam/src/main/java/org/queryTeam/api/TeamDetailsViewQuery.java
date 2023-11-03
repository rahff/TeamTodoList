package org.queryTeam.api;

import org.queryTeam.dto.TeamDto;
import org.queryTeam.dto.TodoListDto;
import org.queryTeam.model.TeamDetails;
import org.queryTeam.model.TeamDetailsViewModel;
import org.queryTeam.model.Teammate;
import org.queryTeam.model.TodoList;
import org.queryTeam.spi.TeamDataAccess;
import org.queryTeam.spi.TodoListDataAccess;
import org.queryTeam.spi.UserDataAccess;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class TeamDetailsViewQuery {

    private final TeamDataAccess teamDataAccess;
    private final TodoListDataAccess todoListDataAccess;
    private final UserDataAccess userDataAccess;

    private ExecutorService executorService;
    public TeamDetailsViewQuery(TeamDataAccess teamDataAccess,
                                TodoListDataAccess todoListDataAccess,
                                UserDataAccess userDataAccess){
        this.teamDataAccess = teamDataAccess;
        this.todoListDataAccess = todoListDataAccess;
        this.userDataAccess = userDataAccess;
    }

    public TeamDetailsViewModel query(final String teamId) throws ExecutionException, InterruptedException {
        try{
            executorService = Executors.newCachedThreadPool();
            Future<TeamDto> teamDto$ = executorService.submit(() -> teamDataAccess.getTeamById(teamId));
            Future<List<String>> availableTeammatesRefs$ = executorService.submit(teamDataAccess::getAvailableTeammatesRef);
            Future<List<TodoListDto>> teamTodoListsDto$ = executorService.submit(() -> todoListDataAccess.getTodoListsByTeamId(teamId));
            Future<List<Teammate>> availableTeammates$ = executorService.submit(() -> getAvailableTeammate(availableTeammatesRefs$));
            Future<List<TodoList>> teamTodoLists$ = executorService.submit(() -> getTeamTodoList(teamTodoListsDto$));
            Future<List<Teammate>> teammatesTeam$ = executorService.submit(() -> getTeammatesTeam(teamDto$, teamId));
            var teamDto = teamDto$.get();
            var teamDetails = new TeamDetails(teamDto.id(), teamDto.name(), teammatesTeam$.get(), teamTodoLists$.get());
            return new TeamDetailsViewModel(teamDetails, availableTeammates$.get());
        }finally {
            executorService.shutdown();
        }
    }
    private List<Teammate> getTeammatesTeam(Future<TeamDto> teamDto$, String teamId) throws ExecutionException, InterruptedException {
        return teamDto$.get().teammateIds()
                .stream().parallel().map(userDataAccess::getTeammateByUserId)
                .map(dto -> new Teammate(dto.id(), dto.name(), dto.email(), Optional.of(teamId))).toList();
    }

    private List<TodoList> getTeamTodoList(Future<List<TodoListDto>> todoListDto$) throws ExecutionException, InterruptedException {
        return todoListDto$.get().stream().map(dto -> new TodoList(dto.id(), dto.name(), dto.createdAt())).toList();
    }

    private List<Teammate> getAvailableTeammate(Future<List<String>> theirIds$) throws ExecutionException, InterruptedException {
        return theirIds$.get().stream().parallel().map(userDataAccess::getTeammateByUserId)
                .map(dto -> new Teammate(dto.id(), dto.name(), dto.email(), Optional.empty()))
                .toList();
    }
}
