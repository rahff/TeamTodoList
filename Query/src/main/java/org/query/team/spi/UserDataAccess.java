package org.query.team.spi;


import org.query.team.dto.TeammateDto;

import java.util.List;
import java.util.Optional;

public interface UserDataAccess {

    Optional<TeammateDto> getTeammateByUserId(String userId);
    List<TeammateDto> getAllTeammate(String accountId);
}
