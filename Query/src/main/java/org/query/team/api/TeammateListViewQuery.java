package org.query.team.api;



import org.query.team.model.Teammate;
import org.query.team.model.TeammateListViewModel;
import org.query.team.spi.TeamDataAccess;
import org.query.team.spi.UserDataAccess;

import java.util.stream.Collectors;

public class TeammateListViewQuery {

    private final UserDataAccess userDataAccess;
    private final TeamDataAccess teamDataAccess;

    public TeammateListViewQuery(UserDataAccess userDataAccess, TeamDataAccess teamDataAccess) {
        this.userDataAccess = userDataAccess;
        this.teamDataAccess = teamDataAccess;
    }

    public TeammateListViewModel query(String accountId) {
        var teammateDto = userDataAccess.getAllTeammate(accountId);
        var teammates = teammateDto.stream().parallel().map((dto)-> {
            var teamId = teamDataAccess.getTeamIdOfTeammate(dto.id());
            return new Teammate(dto.id(), dto.name(), dto.email(), teamId);
        }).collect(Collectors.toList());
        return new TeammateListViewModel(teammates);
    }
}
