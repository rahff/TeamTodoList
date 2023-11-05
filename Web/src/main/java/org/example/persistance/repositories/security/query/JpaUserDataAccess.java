package org.example.persistance.repositories.security.query;


import org.query.team.dto.TeammateDto;
import org.query.team.spi.UserDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Profile("prod")
public class JpaUserDataAccess implements UserDataAccess {

    public Optional<TeammateDto> getTeammateByUserId(String userId) {
        return Optional.empty();
    }


    public List<TeammateDto> getAllTeammate(String accountId) {
        return null;
    }
}
