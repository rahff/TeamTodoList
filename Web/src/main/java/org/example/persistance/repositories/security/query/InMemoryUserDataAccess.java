package org.example.persistance.repositories.security.query;

import org.queryTeam.dto.TeammateDto;
import org.queryTeam.spi.UserDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


@Repository
@Profile("test")
public class InMemoryUserDataAccess implements UserDataAccess {
    public TeammateDto getTeammateByUserId(String userId) {
        return new TeammateDto(userId, "teammateName"+userId, "teammate"+userId+"@gmail.com");
    }
}
