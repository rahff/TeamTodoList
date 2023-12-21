package org.example.persistance.entities.team;

import jakarta.persistence.*;
import org.example.persistance.entities.security.AppUser;
import org.team.ports.dto.TeamDto;


import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "team")
public class Team {
  @Id
  @Column(name = "id", nullable = false, length = 45)
  private String id;
  private String name;
  private String accountId;

  private List<String> teammates;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getTeammates() {
    return teammates;
  }

  public void setTeammates(List<String> teammates) {
    this.teammates = teammates;
  }
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public TeamDto toDto(){

    return new TeamDto(id, name, teammates, accountId);
  }
}
