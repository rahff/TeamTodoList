package org.query.team.api;

import org.query.shared.spi.UserDataAccess;
import org.query.team.model.Teammate;

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
