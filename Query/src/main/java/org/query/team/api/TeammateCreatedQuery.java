package org.query.team.api;

import org.query.team.model.Teammate;
import org.query.team.spi.UserDataAccess;

import java.util.Optional;

public class TeammateCreatedQuery {
    private final UserDataAccess userDataAccess;


    public TeammateCreatedQuery(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public Teammate getTeammate(String itsId) {
        var teammateDto = userDataAccess.getTeammateByUserId(itsId).orElseThrow();
        return new Teammate(teammateDto.id(), teammateDto.name(), teammateDto.email(), null);
    }
}
