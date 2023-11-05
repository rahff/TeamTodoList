package org.example.controllers.query.team;


import org.query.team.api.TeamListViewQuery;
import org.query.team.model.TeamListViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class TeamListViewController {

    private final TeamListViewQuery viewModel;

    public TeamListViewController(TeamListViewQuery viewModelQuery) {
        this.viewModel = viewModelQuery;
    }

    @GetMapping("/teams/{accountId}")

    public TeamListViewModel teamListViewModel(@PathVariable String accountId){
        try{
            return viewModel.query(accountId);
        }catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
