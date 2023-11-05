package org.example.persistance.repositories.team.query;


import org.query.team.dto.TeamDto;
import org.query.team.spi.TeamDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("prod")
public class JpaTeamDataAccess implements TeamDataAccess {

    public Optional<TeamDto> getTeamById(String id) {
        return Optional.empty();
    }


    public List<String> getAvailableTeammatesRef() {
        return null;
    }

    @Override
    public List<TeamDto> getAllTeam(String accountId) {
        return List.of();
    }

    @Override
    public Optional<String> getTeamIdOfTeammate(String teammateId) {
        return Optional.empty();
    }
}
