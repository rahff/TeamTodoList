package org.example.persistance.entities.team;

import jakarta.persistence.*;

@Entity
@Table(name = "teammate")
public class Teammate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String ref;

  public Teammate(String ref) {
    this.ref = ref;
  }

  public Teammate() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }
}
