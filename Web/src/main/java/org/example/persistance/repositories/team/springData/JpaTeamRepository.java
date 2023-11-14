package org.example.persistance.repositories.team.springData;

import org.example.persistance.entities.team.Team;
import org.query.team.dto.TeamDto;
import org.query.team.dto.TodoListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTeamRepository extends JpaRepository<Team, String> {

  List<Team> findByAccountId(String accountId);
}
