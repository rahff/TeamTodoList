package org.example.persistance.repositories.security.query;

import org.query.team.dto.TeammateDto;
import org.query.team.spi.UserDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Profile("test")
public class InMemoryUserDataAccess implements UserDataAccess {
    public Optional<TeammateDto> getTeammateByUserId(String userId) {
        return Optional.of(new TeammateDto(userId, "teammateName"+userId, "teammate"+userId+"@gmail.com"));
    }
    public List<TeammateDto> getAllTeammate(String accountId) {
        return List.of(
                new TeammateDto("teammateId1", "teammateName1", "teammateName1@gmail.com"),
                new TeammateDto("teammateId2", "teammateName2", "teammateName2@gmail.com"),
                new TeammateDto("teammateId3", "teammateName3", "teammateName3@gmail.com"),
                new TeammateDto("teammateId4", "teammateName4", "teammateName4@gmail.com")
        );
    }
}
