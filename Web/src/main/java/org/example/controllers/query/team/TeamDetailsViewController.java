package org.example.controllers.query.team;

import org.query.team.api.TeamDetailsViewQuery;
import org.query.team.model.TeamDetailsViewModel;
import org.shared.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class TeamDetailsViewController {
    private final TeamDetailsViewQuery viewModel;

    public TeamDetailsViewController(TeamDetailsViewQuery queryViewModel) {
        this.viewModel = queryViewModel;
    }

    @GetMapping("/team-details/{id}")
    public TeamDetailsViewModel teamDetailsViewModel(@PathVariable String id, @AuthenticationPrincipal UserDto userDetails){
        try{
            return viewModel.query(id, userDetails.accountId());
        }catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
