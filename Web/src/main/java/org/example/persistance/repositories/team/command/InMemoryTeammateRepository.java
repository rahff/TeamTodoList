package org.example.persistance.repositories.team.command;

import org.example.persistance.entities.team.Teammate;
import org.shared.dto.UserDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.team.ports.spi.TeammateRepository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Profile("test")
public class InMemoryTeammateRepository implements TeammateRepository {

  private final List<Teammate> data;

  public InMemoryTeammateRepository(){
    this.data = new ArrayList<>();
  }
  public void saveUserAsTeammate(UserDto userDto) {
    var teammate = new Teammate(userDto.id(), userDto.accountId(), null);
    data.add(teammate);
  }

  public void addTeamIdOnTeammate(List<String> ids, String teamId) {

  }

  public List<Teammate> items(){
    return data;
  }
}
