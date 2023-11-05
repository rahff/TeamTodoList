package org.query.team.spi;



import org.query.team.dto.TeamDto;

import java.util.List;
import java.util.Optional;


public interface TeamDataAccess {
    Optional<TeamDto> getTeamById(String id);
    List<String> getAvailableTeammatesRef();
    List<TeamDto> getAllTeam(String accountId);
    Optional<String> getTeamIdOfTeammate(String teammateId);
}
