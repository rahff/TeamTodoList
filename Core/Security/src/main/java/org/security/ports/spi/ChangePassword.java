package org.security.ports.spi;

import org.security.ports.dto.ChangePasswordRequest;

public interface ChangePassword {
  void registerNewPassword(ChangePasswordRequest request);
}
