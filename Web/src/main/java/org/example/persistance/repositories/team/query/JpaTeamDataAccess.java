package org.example.persistance.repositories.team.query;

import org.queryTeam.dto.TeamDto;
import org.queryTeam.spi.TeamDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
public class JpaTeamDataAccess implements TeamDataAccess {

    public TeamDto getTeamById(String id) {
        return null;
    }


    public List<String> getAvailableTeammatesRef() {
        return null;
    }
}
