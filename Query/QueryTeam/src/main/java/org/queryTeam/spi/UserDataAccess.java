package org.queryTeam.spi;

import org.queryTeam.dto.TeammateDto;

public interface UserDataAccess {

    TeammateDto getTeammateByUserId(String userId);
}
