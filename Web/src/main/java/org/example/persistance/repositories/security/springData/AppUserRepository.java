package org.example.persistance.repositories.security.springData;

import org.example.persistance.entities.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {
  Optional<AppUser> findByEmail(String email);

  List<AppUser> findByAccountId(String accountId);
}
