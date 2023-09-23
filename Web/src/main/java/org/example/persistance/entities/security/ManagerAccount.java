package org.example.persistance.entities.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager_account")
public class ManagerAccount {
  @Id
  @Column(name = "id", nullable = false, length = 45)
  private String id;

  public ManagerAccount(String id) {
    this.id = id;
  }

  public ManagerAccount() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
