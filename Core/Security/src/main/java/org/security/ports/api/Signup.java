package org.security.ports.api;

import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.SignupUserRequest;
import org.shared.dto.SubscriptionDto;

public interface Signup {
  JwtAuthenticationResult doSignup(SignupUserRequest signupUserRequest, SubscriptionDto subscription) throws Exception;
}
