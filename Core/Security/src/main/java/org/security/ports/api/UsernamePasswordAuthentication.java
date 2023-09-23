package org.security.ports.api;

import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.LoginUserRequest;

public interface UsernamePasswordAuthentication {
  JwtAuthenticationResult loginUser(LoginUserRequest loginUserRequest);
}
