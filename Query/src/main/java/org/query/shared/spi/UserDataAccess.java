package org.query.shared.spi;


import org.query.account.dto.UserDto;
import org.query.team.dto.TeammateDto;

import java.util.List;
import java.util.Optional;

public interface UserDataAccess {

    Optional<TeammateDto> getTeammateByUserId(String userId);
    List<TeammateDto> getAllTeammate(String accountId);
    Optional<UserDto> getUserById(String userId);
}
