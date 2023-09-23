package org.example.persistance.entities.security;

import jakarta.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  public Authority() {}

  public Authority(String userPlan) {
    this.userRole = UserRole.valueOf(userPlan);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserRole() {
    return userRole.name();
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }
}
