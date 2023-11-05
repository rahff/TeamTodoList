package org.example.controllers.query.team;

import org.query.team.api.TeammateListViewQuery;
import org.query.team.model.TeammateListViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class TeammateListViewController {

    private final TeammateListViewQuery viewModel;

    public TeammateListViewController(TeammateListViewQuery viewModel) {
        this.viewModel = viewModel;
    }

    @GetMapping("/teammates/{accountId}")
    public TeammateListViewModel teammateListView(@PathVariable String accountId) {
        try{
            return viewModel.query(accountId);
        }catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
