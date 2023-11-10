package org.team.ports.api;
import org.team.ports.dto.CreateTeammateRequest;
import org.team.ports.dto.TeammateJoiningRequest;


public interface AddTeammate {
  TeammateJoiningRequest execute(CreateTeammateRequest request);
}
