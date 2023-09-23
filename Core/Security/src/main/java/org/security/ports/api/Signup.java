package org.security.ports.api;

import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.SignupUserRequest;

public interface Signup {
  JwtAuthenticationResult doSignup(SignupUserRequest signupUserRequest) throws Exception;
}
