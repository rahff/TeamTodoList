package org.example.persistance.repositories.security.command;

import org.example.persistance.entities.security.ManagerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerAccountRepository extends JpaRepository<ManagerAccount, String> {
}