package org.query.team.api;

import org.query.team.model.Teammate;
import org.query.team.spi.UserDataAccess;

import java.util.List;
import java.util.Optional;

public class TeammatesAddedQuery {

    private final UserDataAccess userDataAccess;

    public TeammatesAddedQuery(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public List<Teammate> getTeammates(List<String> theirIds, final String teamId) {
        var teammatesDto = theirIds.stream().parallel()
                .map(id -> userDataAccess.getTeammateByUserId(id).orElseThrow());
        return teammatesDto.map(dto -> new Teammate(dto.id(), dto.name(), dto.email(), Optional.of(teamId))).toList();
    }
}
