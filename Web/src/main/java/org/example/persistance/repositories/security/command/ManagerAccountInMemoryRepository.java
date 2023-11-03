package org.example.persistance.repositories.security.command;

import org.security.ports.spi.AccountRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

import java.util.Set;

@Repository
@Profile("test")
public class ManagerAccountInMemoryRepository implements AccountRepository {

  private final Set<String> data = new HashSet<>();
  public String save(String id) {
    data.add(id);
    return id;
  }
}
