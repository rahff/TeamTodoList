package org.example.persistance.repositories.team.springData;

import org.example.persistance.entities.team.Teammate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaTeammateRepository extends JpaRepository<Teammate, Long> {
  List<Teammate> findByAccountIdAndTeamIdIsNull(String accountId);

    List<Teammate> findByRef(String teammateId);

  void deleteByRef(String userId);
}
