package org.example.persistance.repositories.security;

import org.example.persistance.entities.security.ManagerAccount;
import org.security.ports.spi.AccountRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")
public class JpaManagerAccountRepositoryAdapter implements AccountRepository {

  private final ManagerAccountRepository accountRepository;

  public JpaManagerAccountRepositoryAdapter(ManagerAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public String save(String id) {
    var entity = new ManagerAccount(id);
    var savedAccount = accountRepository.save(entity);
    return savedAccount.getId();
  }
}
