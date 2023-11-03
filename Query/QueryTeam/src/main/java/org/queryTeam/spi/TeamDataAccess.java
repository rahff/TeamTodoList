package org.queryTeam.spi;

import org.queryTeam.dto.TeamDto;

import java.util.List;


public interface TeamDataAccess {
    TeamDto getTeamById(String id);
    List<String> getAvailableTeammatesRef();
    List<TeamDto> getAllTeam(String accountId);
}
