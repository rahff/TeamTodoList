package org.example.persistance.entities.team;

import jakarta.persistence.*;

@Entity
@Table(name = "teammate")
public class Teammate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(unique = true)
  private String ref;

  private String accountId;
  private String teamId;

  public Teammate(String ref, String accountId, String teamId) {
    this.ref = ref;
    this.accountId = accountId;
    this.teamId = teamId;
  }

  public Teammate() {
  }
  public String getRef() {
    return ref;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getTeamId() {
    return teamId;
  }

  public Long getId() {
    return id;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }
}
