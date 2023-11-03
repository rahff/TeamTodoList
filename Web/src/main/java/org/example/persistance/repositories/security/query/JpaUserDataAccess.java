package org.example.persistance.repositories.security.query;

import org.queryTeam.dto.TeammateDto;
import org.queryTeam.spi.UserDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


@Repository
@Profile("prod")
public class JpaUserDataAccess implements UserDataAccess {

    public TeammateDto getTeammateByUserId(String userId) {
        return null;
    }
}
