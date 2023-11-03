package org.example.persistance.repositories.todo.query;

import org.queryTeam.dto.TeammateDto;
import org.queryTeam.spi.UserDataAccess;

public class InMemoryUserDataAccess implements UserDataAccess {
    public TeammateDto getTeammateByUserId(String userId) {
        return new TeammateDto(userId, "teammateName"+userId, "teammate"+userId+"@gmail.com");
    }
}
