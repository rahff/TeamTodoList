package org.example.persistance.entities.team;

import jakarta.persistence.*;
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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Teammate> teammates;

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

  public List<Teammate> getTeammates() {
    return teammates;
  }

  public void setTeammates(List<Teammate> teammates) {
    this.teammates = teammates;
  }
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public TeamDto toDto(){
    var teammateList = teammates.stream()
      .map(Teammate::getRef).collect(Collectors.toList());
    return new TeamDto(id, name, teammateList, accountId);
  }
}
