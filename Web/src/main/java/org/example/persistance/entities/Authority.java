package org.example.persistance.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  private UserPlan userPlan;

  public Authority() {}

  public Authority(String userPlan) {
    this.userPlan = UserPlan.valueOf(userPlan);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserPlan() {
    return userPlan.name();
  }

  public void setUserPlan(UserPlan userPlan) {
    this.userPlan = userPlan;
  }
}
