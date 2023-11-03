package org.example.persistance.repositories.team.command;

import org.example.persistance.entities.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeamRepository extends JpaRepository<Team, String> {
}
