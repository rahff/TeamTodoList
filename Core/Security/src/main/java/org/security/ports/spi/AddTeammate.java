package org.security.ports.spi;

import org.security.ports.dto.CreateTeammateRequest;
import org.security.ports.dto.TeammateJoiningRequest;

public interface AddTeammate {
  TeammateJoiningRequest execute(CreateTeammateRequest request);
}
